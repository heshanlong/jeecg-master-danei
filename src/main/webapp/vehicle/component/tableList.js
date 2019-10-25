document.write('<script src="./component/pagination.js"></script>')
document.write('<link rel="stylesheet" href="./css/tableList.css"/>')
Vue.component('table-list', {
  template: `
    <div class="tableList">
      <div class="table">
        <el-table :data="dataSource" @sort-change="sortChange" ref="table"
                  height="100%" :empty-text="emptyText" stripe border 
                  style="width: 100%;" v-show="tableStyle" @row-click="rowClick">
          <el-table-column width="60" align="center" v-if="showSelect">
            <template #header>
                <input type="checkbox" ref="selectAll" @change="selectAll" v-model="selectedAll">
            </template>
            <template #default="{$index}">
              <input type="checkbox" :value="$index" v-model="selected">
            </template>
          </el-table-column>
          <el-table-column align="center" type="index" label="序号" width="55"></el-table-column>
          <template v-for="(item, index) in headerList" v-if="item.show">
            <el-table-column :min-width="item.width" :label="item.label" :prop="item.prop"
                              sortable="custom" #default="{row}" v-if="!item.operate">
              {{ item.func ? item.func(row[item.prop]) : row[item.prop] }}
            </el-table-column>
            <el-table-column align="center" :label="item.label" :min-width="item.width" v-else #default="{row}">
                <slot :name="'operate' + (index === headerList.length - 1 ? '' : index)" :data="row"></slot>
            </el-table-column>
          </template>
        </el-table>
        <ul class="list" v-show="!tableStyle">
          <li v-if="!dataSource.length" class="empty" v-cloak>{{ emptyText }}</li>
          <li v-for="(data, index) in dataSource" :key="index" v-else>
              <label>
                <p v-if="showSelect"><input type="checkbox" :value="index" v-model="selected"></p>
                <p><span>序号：</span><span>{{ index + 1 }}</span></p>
                <template v-for="item in headerList" v-if="item.show">
                  <p v-if="!item.operate">
                    <span>{{ item.label }}：</span><span>{{ item.func ? item.func(data[item.prop]) : data[item.prop] }}</span>
                  </p>
                  <p v-else>
                    <span>{{ item.label }}：</span><slot name="operate" :data="data"></slot>
                  </p>
                </template>
              </label>
            </li>
        </ul>
      </div>
      <pagination ref="pagination" :total="total" :pages="pages" v-show="total && showPagination"></pagination>
    </div>
  `,
  props: {
    headerList: {
      type: Array,
      required: true,
    },
    dataSource: {
      type: Array,
      required: true
    },
    tableStyle: {
      type: Boolean,
      default: true
    },
    loading: Boolean,
    showSelect: Boolean,
    total: Number,
    pages: Number,
    showPagination: {
      type: Boolean,
      default: true
    }
  },
  inject: ['sortChange'],
  data () {
    return {
      selectedAll: false,
      selected: []
    }
  },
  computed: {
    size () {
      return this.$refs.pagination.size
    },
    current () {
      return this.$refs.pagination.current
    },
    emptyText () {
      return this.loading ? '正在努力加载数据中，请稍后...' : '没有找到匹配的记录'
    }
  },
  watch: {
    selected (value) {
      this.selectedAll = value.length && (value.length === this.dataSource.length)
      this.$emit('selected-change', value)
    },
    dataSource () {
      this.selected = []
    }
  },
  methods: {
    clearSelect () {
      this.selected = []
    },
    rowClick (row) {
      let i = this.dataSource.indexOf(row)
      let index = this.selected.indexOf(i)
      if (index !== -1) this.selected.splice(index, 1)
      else this.selected.push(i)
    },
    selectAll () {
      this.selected = this.selectedAll ? this.dataSource.map((_, index) => index) : []
    },
    getData (pageNo, size) {
      this.$emit('get-data', pageNo, size)
    },
    doLayout () {
      this.$nextTick(() => {
        this.tableStyle && this.$refs.table.doLayout()
      })
    }
  }
})
