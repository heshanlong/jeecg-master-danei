(function(){
  let PrintMap = {}
  let LAYER_COUNT = 0
  let LAYER_LENGTH = 0
  window.MapToImg = PrintMap

  PrintMap.excute = function(map){
    let canvas = document.createElement('canvas')
    let broz = SuperMap.Browser.name
    if(!canvas.getContext || (broz === 'msie' && !canvas.msToBlob)){
      alert('您的浏览器版本太低，请升级。')
      return
    }
    if(document.location.toString().match(/file:\/\//)){
      alert('该功能需要在服务器中发布出来后，方可使用')
      return
    }
    LAYER_COUNT = 0

    let layers = map.layers.concat([])

    //layers排序，将markers放到最上边
    let layers1 = []
    for (let i = 0; i < layers.length; ) {
      if (layers[i].CLASS_NAME === 'SuperMap.Layer.Markers'){
        let templayer = layers.splice(i,1)
        layers1.push(templayer[0])
      } else i++
    }
    layers = layers.concat(layers1)

    LAYER_LENGTH = layers.length
    let imgUrls = []
    for (let i = 0; i < layers.length; i++) {
      let layer = layers[i]
      if (layer.CLASS_NAME === 'SuperMap.Layer.TiledDynamicRESTLayer') {
        if (layer.useCanvas === false) draw(map, getImgLayerData(layer,map),i,imgUrls)
        else draw(map, getCanvasLayerData(layer),i,imgUrls)
      } else if (layer.CLASS_NAME === 'SuperMap.Layer.Markers') {
        draw(map, getImgLayerData(layer,map), i, imgUrls)
      } else if (layer.CLASS_NAME === 'SuperMap.Layer.Vector'){
        getVectorLayerData(layer, map, ((imgUrls, i) =>
          img => { draw(map, img,i,imgUrls) }
        )(imgUrls, i))
      } else if (layer.CLASS_NAME === 'SuperMap.Layer.CloudLayer') {
        draw(map, getCanvasLayerData(layer), i, imgUrls)
      } else if (layer.CLASS_NAME === 'SuperMap.Layer.HeatMapFastLayer') {
        draw(map, getCanvasLayerData(layer), i, imgUrls)
      }
    }
  }

  function draw (map, img, i, imgUrls) {
    imgUrls[i] = img
    LAYER_COUNT++
    if (LAYER_COUNT >= LAYER_LENGTH) {
      let canvas = document.createElement('canvas')
      let size = map.getSize()
      canvas.height = size.h
      canvas.width = size.w
      let ctx = canvas.getContext('2d')

      canvas.style.position = 'relative'
      canvas.style.border = '1px solid #4c4c4c'

      let panel = document.createElement('div')

      panel.style.position = 'absolute'
      panel.style.left = '0px'
      panel.style.top = '0px'
      panel.style.height = '100%'
      panel.style.width = '100%'
      panel.style.background = '#ffffff'
      document.body.appendChild(panel)


      let buttonPanel = document.createElement('div')
      buttonPanel.style.position = 'relative'
      panel.appendChild(buttonPanel)
      panel.appendChild(canvas)

      window.setTimeout(function () {
        for (let i = 0; i<imgUrls.length; i++) ctx.drawImage(imgUrls[i],0,0)
        if (canvas.msToBlob) {
          let button = document.createElement('input')
          buttonPanel.appendChild(button)
          button.type = 'button'
          button.value = '保存'
          button.onclick = () => {
            window.navigator.msSaveBlob(canvas.msToBlob(), 'map.png')
          }
        } else {
          let aa = document.createElement('a')
          buttonPanel.appendChild(aa)
          aa.target = '_blank'
          aa.download='map.png'
          aa.href=canvas.toDataURL()

          let button = document.createElement('input')
          aa.appendChild(button)
          button.type = 'button'
          button.value = '保存'
        }

        let button = document.createElement('input')
        buttonPanel.appendChild(button)
        button.type = 'button'
        button.value = '关闭'
        button.onclick = () => {document.body.removeChild(panel)}
      },30)
    }
  }
  //截取图片图层
  function getImgLayerData (layer,map) {
    let div = layer.div
    let pdiv = div.parentNode
    let offsetX =  parseInt(pdiv.style.left.replace(/px/,''))
    let offsetY =  parseInt(pdiv.style.top.replace(/px/,''))

    let canvas = document.createElement('canvas')
    let size = map.getSize()
    canvas.height = size.h
    canvas.width = size.w
    let ctx = canvas.getContext('2d')

    canvas.style.position = 'absolute'
    canvas.style.left = '5px'
    canvas.style.top = '600px'
    canvas.style.border = '1px solid #f00'

    let divs = div.getElementsByTagName('div')
    for (let i = 0; i < divs.length; i++) {
      let div1 = divs[i]
      if (div1.style.display !== 'none') {
        let left = parseInt(div1.style.left.replace(/px/,''))
        let top = parseInt(div1.style.top.replace(/px/,''))
        let img = div1.getElementsByTagName('img')[0]
        let imgWidth = img.style.width
        let imgHeight = img.style.height
        let imgW = null,imgH = null
        if (imgWidth !== null || imgWidth !== '') imgW = parseInt(imgWidth.replace(/px/,''))
        if (imgHeight !== null || imgHeight !== '') imgH = parseInt(imgHeight.replace(/px/,''))
        if (imgW !== null && imgH !== null) ctx.drawImage(img,left+offsetX,top+offsetY,imgW,imgH)
        else ctx.drawImage(img,left+offsetX,top+offsetY)
      }
    }
    let imageUrl = canvas.toDataURL('image/png')
    let img = new Image()
    img.src = imageUrl
    return img
  }
  //截取canvas图层
  function getCanvasLayerData(layer){
    let div = layer.div
    let canvas0 = div.getElementsByTagName('canvas')[0]
    let imageUrl = canvas0.toDataURL('image/png')
    let img = new Image()
    img.src = imageUrl
    return img
  }
  //截取Vector图层
  function getVectorLayerData (layer,map,callback) {
    let printLayer, strategies = [], features1 = []
    let features = layer.features
    let layerStrategies = layer.strategies
    //GeoText无法截图问题修复
    if (layerStrategies) {
      for (let i = 0; i < layerStrategies.length; i++){
        if (layerStrategies[i].CLASS_NAME === 'SuperMap.Strategy.GeoText') strategies.push(layerStrategies[i].clone())
        else strategies.push(layerStrategies[i])
      }
      printLayer = new SuperMap.Layer.Vector('PRINT_LAYER', {strategies: strategies, visibility: true, renderers: ['Canvas']})
    } else printLayer = new SuperMap.Layer.Vector('PRINT_LAYER', {visibility: true, renderers: ['Canvas']})
    map.addLayer(printLayer)
    for (let j = 0; j < features.length; j++){
      let feature = features[j]
      let feature1 = new SuperMap.Feature.Vector()
      feature1.geometry = feature.geometry.clone()
      feature1.style = feature.style
      features1.push(feature1)
    }
    if (layer.style) printLayer.style = layer.style
    printLayer.setOpacity(0)
    printLayer.addFeatures(features1)
    window.setTimeout(((printLayer, map, callback) =>
      () => {
        let div = printLayer.div
        let canvas1 = div.getElementsByTagName('canvas')[0]
        canvas1.getContext('2d')
        let imageUrl = canvas1.toDataURL('image/png')
        map.removeLayer(printLayer)
        printLayer.destroy()
        let img = new Image()
        img.src = imageUrl
        callback(img)
      })(printLayer, map, callback), 30)
  }
})()
