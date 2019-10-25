// axios.defaults.baseURL = 'http://www.xyzt8.top:8010'
axios.defaults.baseURL = 'http://119.23.58.71:8888'
// axios.defaults.withCredentials = true

axios.interceptors.request.use(config => {
  if (['post', 'put'].includes(config.method) && config.data)
    config.data = Qs.stringify(config.data, {arrayFormat: 'repeat'})
  return config
}, error => {
  return Promise.reject(error)
})

axios.interceptors.response.use(response => {
  let data = response.data
  if (data.code === 200) return data
  else warning(data.message)
}, error => {
  return Promise.reject(error)
})

let get = (url, params) => axios.get(url, {params, paramsSerializer: p => Qs.stringify(p, { indices: false })})

let del = (url, params) => axios.delete(url, {params, paramsSerializer: p => Qs.stringify(p, { indices: false })})

let post = (url, params) => axios.post(url, params)

let put = (url, params) => axios.put(url, params)
