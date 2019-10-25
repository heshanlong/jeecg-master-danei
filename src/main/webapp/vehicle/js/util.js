let time = 3000

let showMsg = (msg, icon) => {
  layui.use('layer', () => {
    let layer = layui.layer
    layer.open({
      title: '提示信息',
      content: msg,
      btn: [],
      offset: 'rb',
      time,
      shade: false,
      icon,
      anim: 2,
      isOutAnim: false
    })
  })
}

let warning = msg => { showMsg(msg, 0) }
let success = msg => { showMsg(msg, 1) }

let delConfirm = () => {
  return new Promise(resolve => {
    layui.use('layer', () => {
      let layer = layui.layer
      layer.open({
        title: '删除确认',
        content: '确定删除该记录吗？',
        btn: ['确认', '取消'],
        yes: index => {
          resolve()
          layer.close(index)
        },
        icon: 0,
        anim: 0
      })
    })
  })
}

let getTodayTime = isOver => {
  let date = new Date()
  date.setHours(0, 0, 0)
  if (isOver) date.setHours(23, 59, 59)
  return dateToString(date)
}

let dateToString = date => {
  let year = date.getFullYear()
  let month = datePad(date.getMonth() + 1)
  let day = datePad(date.getDate())
  let hour = datePad(date.getHours())
  let min = datePad(date.getMinutes())
  let sec = datePad(date.getSeconds())
  return `${year}-${month}-${day} ${hour}:${min}:${sec}`
}

let datePad = s => s.toString().padStart(2, '0')

let getToday = type => {
  let now = new Date()
  let year = now.getFullYear().toString()
  if (type === 'year') return year
  let month = datePad(now.getMonth() + 1)
  if (type === 'month') return `${year}-${month}`
  let date = datePad(now.getDate())
  return `${year}-${month}-${date}`
}


let getUserName = () => {
  let cookie = document.cookie
  let cookieArray = cookie.split(';')
  let userNameCookie = cookieArray.find(item => item.includes('userName'))
  if (!userNameCookie) return ''
  return userNameCookie.split('=')[1]
}

let getContentHTML = vehicle => {
  let {vehiclePlate, itemName, company, locationTime, locationSpeed, mileage} = vehicle
  let speed = parseInt(locationSpeed / 10) + 'km/h'
  let actualMileage = parseInt(mileage / 10) + 'km'
  return `
    <div class="vehicleInfo">
        <div class="bold">${vehiclePlate}</div>
        <div><span class="bold">车辆类型：</span><span>${itemName}</span></div>
        <div><span class="bold">所属单位：</span><span>${company}</span></div>
        <div class="inline">
           <p><span class="bold">时间：</span><span>${locationTime}</span></p>
           <p><span class="bold">速度：</span><span>${speed}</span></p>
        </div>
        <div><span class="bold">里程：</span><span>${actualMileage}</span></div>
    </div>
  `
}

let getVehicleType = async () => {
  let vehicleType = localStorage.getItem('vehicleType')
  if (vehicleType) return JSON.parse(vehicleType)
  else {
    let res = await getVehicleTypes()
    let vehicleType = res.data
    localStorage.setItem('vehicleType', JSON.stringify(vehicleType))
    return vehicleType
  }
}

let updateVehicleType = async () => {
  let res = await getVehicleTypes()
  let vehicleType = res.data
  localStorage.setItem('vehicleType', JSON.stringify(vehicleType))
}

let getVehicleTypeById = async id => {
  let vehicleTypeList = await getVehicleType()
  for (let vehicleType of vehicleTypeList) {
    let type = vehicleType.items.find(item => item.id === id)
    if (type) return type
  }
}

let transTime = timestamp => {
  let result = ''
  let second = timestamp / 1000
  if (second > 86400) {
    let day = Math.floor(second / 86400)
    result += `${day}天`
    second %= 86400
  }
  if (second > 3600) {
    let hour = Math.floor(second / 3600)
    result += `${hour}时`
    second %= 3600
  }
  if (second > 60) {
    let min = Math.floor(second / 60)
    result += `${min}分`
    second %= 60
  }
  if (second >= 0) result += `${second}秒`
  return result
}

let getTime = second => {
  let h = Math.floor((second / 3600)).toString().padStart(2, '0')
  second %= 3600
  let m = Math.floor((second / 60)).toString().padStart(2, '0')
  second %= 60
  let s = Math.floor(second).toString().padStart(2, '0')
  return `${h}:${m}:${s}`
}

let getSecond = time => {
  let timeArr = time.split(':')
  let h = parseInt(timeArr[0])
  let m = parseInt(timeArr[1])
  let s = parseInt(timeArr[2])
  return h * 3600 + m * 60 + s
}

let getViolationType = code => {
  let typeList = [
    {code: 10, content: '施工围栏'},
    {code: 100, content: '跨越围栏禁入'},
    {code: 101, content: '跨越围栏禁出'},
    {code: 200, content: '限速围栏超速100%以上'},
    {code: 201, content: '限速围栏超速50%以上'},
    {code: 202, content: '限速围栏超速50%以下'},
    {code: 300, content: '禁停围栏'},
    {code: 400, content: '通行区围栏'}
  ]
  let type = typeList.find(item => item.code === code)
  return type ? type.content : ''
}

// 添加车辆标记
let addVehicleIcon = (layer, coordinate, direction, clickHandler) => addMarker(
  layer,
  coordinate,
  {width: 32, height: 34},
  getImage(direction),
  clickHandler
)


// 添加轨迹点
let addTrackPoint = (layer, coordinate, clickHandler) => addMarker(
  layer,
  coordinate,
  {width: 12, height: 12},
  './image/point.png',
  clickHandler
)

let getImage = d => {
  let direction = ''
  if (d >= 0 && d <= 22.5) direction = 'north'
  else if (d <= 67.5) direction = 'northeast'
  else if (d <= 112.5) direction = 'east'
  else if (d <= 157.5) direction = 'southeast'
  else if (d <= 202.5) direction = 'south'
  else if (d <= 247.5) direction = 'southwest'
  else if (d <= 292.5) direction = 'west'
  else if (d <= 337.5) direction = 'northwest'
  else direction = 'north'
  return `./image/${direction}.png`
}

let throttle = (fn, ...args) => {
  let now = new Date()
  if (!fn.time) {
    fn.time = now
    fn(...args)
    return
  }
  if (now - fn.time >= 200) {
    clearTimeout(fn.timer)
    fn(...args)
    fn.time = now
  } else {
    clearTimeout(fn.timer)
    fn.timer = setTimeout(() => {
      fn(...args)
      fn.time = new Date()
    }, 200)
  }
}
