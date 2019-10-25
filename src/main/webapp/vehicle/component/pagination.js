document.write('<link rel="stylesheet" href="./css/pagination.css"/>')

Vue.component('pagination', {
  template: `
    <div class="pagination">
      <p class="info">
        显示第 {{ startNo }} 到第 {{ endNo }} 条记录，总共 {{ total }} 条记录
        <span>
          每页显示
          <select v-model="size" placeholder="请选择" @change="changeSize">
            <option value="10">10</option>
            <option value="25">25</option>
            <option value="50">50</option>
            <option value="100">100</option>
          </select>
          条记录
        </span>
      </p>
      <p class="page" v-show="pages > 1">
        <button @click="changePage(1)" :disabled="current<=1" class="margin start">«</button>
        <button @click="changePage(-1)" :disabled="current<=1">‹</button>
        <button v-for="item in currentList" @click="changePage(item)" :disabled="current===item" :class="{active: current===item}">{{ item }}</button>
        <button @click="changePage(-2)" :disabled="current>=pages">›</button>
        <button @click="changePage(-3)" :disabled="current>=pages" class="margin end">»</button>
      </p>
    </div>
  `,
  props: ['total', 'pages'],
  data () {
    return {
      size: 10,
      current: 1
    }
  },
  inject: {
    getData: {
      default: () => {}
    }
  },
  computed: {
    startNo () {
      return (this.current - 1) * this.size + 1
    },
    endNo () {
      if (this.current === this.pages) return this.total
      return this.current * this.size
    },
    currentList () {
      if (this.pages <= 5) return this.pages
      else if (this.pages > 5 && this.current <= 3) return 5
      let diff = this.pages - this.current
      let currentList = []
      if (diff >= 2) {
        for (let i = -2; i <= 2; i++) currentList.push(this.current + i)
      } else {
        let start = diff - 5 + 1
        for (let i = start; i <= diff; i++) currentList.push(this.current + i)
      }
      return currentList
    }
  },
  methods: {
    changeSize () {
      let newPages = Math.ceil(this.total / this.size)
      if (this.current > newPages) this.current = newPages
      this.getData(this.current, this.size)
    },
    changePage (b) {
      if (b === -1) this.current--
      else if (b === -2) this.current++
      else if (b === -3) this.current = this.pages
      else this.current = b
      this.getData(this.current, this.size)
    }
  }
})
