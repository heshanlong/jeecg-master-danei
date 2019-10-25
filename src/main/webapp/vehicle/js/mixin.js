let mixin = {
  data () {
    return {
      showSearch: false,
      loading: false,
      originalParams: {},
      pages: 1,
      total: 0,
      dataSource: [],
      originalData: [],
      tableStyle: true,
      headerList: [],
      showHeaderList: [1, 2, 3, 4, 5, 6, 7, 8],
      getDataMethod: ''
    }
  },
  provide () {
    return {
      getData: this.getData,
      sortChange: this.sortChange
    }
  },
  mounted () {
    this.originalParams = {...this.params}
    let content = document.querySelector('.content')
    if (!content) return
    content.addEventListener('click', () => {
      this.$refs.control && this.$refs.control.hiddenList()
    })
  },
  methods: {
    reset (...args) {
      this.params = {...this.originalParams}
      args.forEach(key => {
        let value = this.$data[key]
        if (!value) return
        if (value.constructor === String) this.$data[key] = ''
        else if (value.constructor === Array) this.$data[key] = []
      })
      this.getData(1)
    },
    async getData (current = 1, size) {
      if(typeof this.getDataMethod !== 'function') return
      if (!size) size = this.$refs.tableList.size
      let params = {
        current,
        size,
        ...this.params
      }
      this.dataSource = []
      this.loading = true
      let res = await this.getDataMethod(params)
      if (!res) {
        warning('获取数据失败')
        return
      }
      let {pages, total, records} = res.data
      this.loading = false
      this.pages = pages
      this.total = total
      this.originalData = records
      this.dataSource = [...records]
    },
    changeHeader () {
      this.$refs.tableList.doLayout()
    },
    sortChange ({prop, order}) {
      if (!order) {
        this.dataSource = [...this.originalData]
        return
      }
      this.dataSource.sort((obj1, obj2) => {
        return order === 'ascending' ? (obj1[prop] > obj2[prop] ? 1 : -1) : (obj1[prop] < obj2[prop] ? 1 : -1)
      })
    }
  }
}
