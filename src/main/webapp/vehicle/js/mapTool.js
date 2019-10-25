document.write('<script src="http://iclient.supermap.io/web/libs/iclient8c/libs/SuperMap.Include.js"></script>')
document.write('<script src="http://iclient.supermap.io/dist/classic/iclient-classic.js"></script>')
let initMap = id => {
  // let map, layer
  // map = new SuperMap.Map(id, {
  //   controls: [
  //     new SuperMap.Control.LayerSwitcher(),
  //     new SuperMap.Control.Zoom(),
  //     new SuperMap.Control.Navigation()
  //   ]
  // })
  // layer = new SuperMap.Layer.CloudLayer({useCORS: true})
  // map.addLayers([layer])
  // map.setCenter(new SuperMap.LonLat(12612810.036696, 2678826.3571059), 10)
  // return map

  // let url = 'http://support.supermap.com.cn:8090/iserver/services/map-china400/rest/maps/China'
  let url = 'http://10.10.220.16:8090/maps/飞行区道面巡检专题图'
  let layer = new SuperMap.Layer.TiledDynamicRESTLayer('flightrun', url, {}, {maxResolution: "auto", useCanvas: false, useCORS: true});
  let map = new SuperMap.Map(id, {
    controls: [
      new SuperMap.Control.LayerSwitcher(),
      new SuperMap.Control.OverviewMap(),
      new SuperMap.Control.ScaleLine(),
      new SuperMap.Control.Zoom(),
      new SuperMap.Control.Navigation()
    ]
  })
  layer.events.on({'layerInitialized': () => {
      map.addLayers([layer])
      let center = coordinateTransform(113.3, 23.39, true)
      map.setCenter(new SuperMap.LonLat(center.lon, center.lat), 12)
  }})
  return map
}

// 添加矢量图层
let addVectorLayer = (map, strokeWidth = 1) => {
  let vectorLayer = new SuperMap.Layer.Vector('vectorLayer')
  vectorLayer.style = {
    fillColor: '#75b7ff',
    fillOpacity: 0.3,
    strokeColor: '#75b7ff',
    strokeWidth
  }
  map.addLayer(vectorLayer)
  return vectorLayer
}

// 鼠标绘制图形
let drawGeometry = (map, vector, type) => {
  return new Promise(resolve => {
    let handler = type ? SuperMap.Handler.Polygon : SuperMap.Handler.RegularPolygon
    let drawFeature = new SuperMap.Control.DrawFeature(vector, handler)
    map.addControl(drawFeature)
    drawFeature.events.on({featureadded: ({feature}) => {
        drawFeature.deactivate()
        let points = feature.geometry.getVertices()
        let coordinates = points.map(point => {
          let res = coordinateTransform(point.x, point.y)
          return {longitude: res.lon, latitude: res.lat}
        })
        resolve({feature, coordinates})
      }})
    drawFeature.activate()
  })
}

// 图形绘制
let addGeometry = (map, vector, coordinates, isLine) => {
  let points = coordinates.map(coordinate => {
    let point = coordinateTransform(coordinate.longitude, coordinate.latitude, true)
    return new SuperMap.Geometry.Point(point.lon, point.lat)
  })
  let line = isLine ? new SuperMap.Geometry.LineString(points) : new SuperMap.Geometry.LinearRing(points)
  let polygonVector = new SuperMap.Feature.Vector(line)
  vector.addFeatures([polygonVector])
}

// 添加标记图层
let addMarkerLayer = (map, name = 'markerLayer') => {
  let markerLayer = new SuperMap.Layer.Markers(name)
  map.addLayer(markerLayer)
  return markerLayer
}

// 添加标注
let addMarker = (layer, coordinate, measure, imagePath, clickHandler) => {
  let size = new SuperMap.Size(measure.width, measure.height)
  let offset = new SuperMap.Pixel(-(size.w / 2), -(size.h / 2))
  let icon = new SuperMap.Icon(imagePath, size, offset)
  let anchor = coordinateTransform(coordinate.lon, coordinate.lat, true)
  let marker = new SuperMap.Marker(getLonlat(anchor), icon)
  marker.events.on({'click': clickHandler})
  layer.addMarker(marker)
  return marker
}

// 添加热力图层
let addHeatLayer = map => {
  let heatLayer = new SuperMap.Layer.HeatMapFastLayer('heatLayer')
  heatLayer.radius = 30
  map.addLayer(heatLayer)
  return heatLayer
}

// 添加热点信息
let addHotSpot = (layer, points) => {
  let features = points.map(point => {
    let factPoint = coordinateTransform(point.longitude, point.latitude, true)
    let geometry = new SuperMap.Geometry.Point(factPoint.lon, factPoint.lat)
    return new SuperMap.Feature.Vector(geometry)
  })
  layer.addFeatures(features)
}

// 添加车牌信息框
let addTextInfoWin = (map, marker, plateNo) => {
  let popup = new SuperMap.Popup('plateNo', marker.getLonLat(), null, plateNo)
  popup.setBorder('1px solid lightgrey')
  map.addPopup(popup)
}

let popup
// 添加车辆详情信息框
let addInfoWin = (map, marker, content) => {
  popup && map.removePopup(popup)
  popup = new SuperMap.Popup.FramedCloud('info', marker.getLonLat(), null, content, null, true);
  map.addPopup(popup)
}

let coordinateTransform = (lon, lat, inverse) => {
  let lonLat = new SuperMap.LonLat(lon, lat)
  if (!inverse) lonLat.transform('EPSG:3857', 'EPSG:4326')
  else lonLat.transform('EPSG:4326', 'EPSG:3857')
  return {lon: lonLat.lon, lat: lonLat.lat}
}

let getLonlat = p => new SuperMap.LonLat(p.lon, p.lat)
