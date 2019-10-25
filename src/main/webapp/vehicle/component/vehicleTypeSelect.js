document.write('<link rel="stylesheet" href="./css/select.css"/>')
Vue.component('vehicle-type-select', {
  template: `
    <div class="select">
      <el-tabs v-model="activeName">
        <el-tab-pane label="车辆类型列表" name="vehicleTypeList">
          <p class="top">
            <span>控制区驾驶证车型代号：</span>
            <el-select v-model="categoryId" @change="getSubTypes">
                <el-option v-for="item in vehicleTypeList" :label="item.typeName" :value="item.id" :key="item.id"></el-option>
            </el-select>
          </p>
          <table-list @selected-change="selectedItems=$event" :data-source="dataSource" ref="tableList"
                      :show-select="true" :header-list="headerList" showPagination>
          </table-list>
        </el-tab-pane>
        <el-tab-pane label="已分配车辆类型" name="allocated">
           <el-table :data="currSelVehicleTypes" border height="calc(100% - 10px)" empty-text="没有找到匹配的记录">
            <el-table-column label="序号" align="center" type="index"></el-table-column>
            <el-table-column label="车辆类型" prop="itemName"></el-table-column>
            <el-table-column label="操作" align="center" #default="{row}">
              <button class="remove mini" @click="remove(row)">删除</button>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
      <p class="btn"><button class="sure" @click="selectFinish">确定</button></p>
     </div>
  `,
  props: ['selVehicleTypes'],
  data () {
    return {
      categoryId: '',
      activeName: 'vehicleTypeList',
      currSelVehicleTypes: [],
      headerList: [],
      dataSource: [],
      originalDataSource: [],
      vehicleTypeList: [],
      selectedItems: []
    }
  },
  provide () {
    return {
      sortChange: this.sortChange
    }
  },
  watch: {
    selVehicleTypes (value) {
      this.currSelVehicleTypes = [...value]
    }
  },
  mounted () {
    this.currSelVehicleTypes = [...this.selVehicleTypes]
    this.getVehicleType()
    this.headerList = [
      {label: '车辆类型', prop: 'itemName', width: 100, show: true}
    ]
  },
  methods: {
    async getVehicleType () {
      this.vehicleTypeList = await getVehicleType()
      this.categoryId = this.vehicleTypeList[0].id
      this.getSubTypes()
    },
    getSubTypes () {
      let subTypes = this.vehicleTypeList.find(item => item.id === this.categoryId)
      this.dataSource = subTypes.items || []
      console.log(this.dataSource)
    },
    sortChange ({prop, order}) {
      if (!order) {
        this.dataSource = [...this.originalData]
        return
      }
      this.dataSource.sort((obj1, obj2) => {
        return order === 'ascending' ? (obj1[prop] > obj2[prop] ? 1 : -1) : (obj1[prop] < obj2[prop] ? 1 : -1)
      })
    },
    selectFinish () {
      let selVehicleTypeList = []
      this.selectedItems.forEach(index => {
        selVehicleTypeList.push(this.dataSource[index])
      })
      selVehicleTypeList.forEach(item => {
        let res = this.currSelVehicleTypes.find(d => d.id === item.id)
        if (!res) this.currSelVehicleTypes.push(item)
      })
      this.$emit('select-finish', this.currSelVehicleTypes)
    },
    reset () {
      this.activeName = 'vehicleTypeList'
      this.$refs.tableList.clearSelect()
      this.currSelVehicleTypes = [...this.selVehicleTypes]
    },
    remove (row) {
      let index = this.currSelVehicleTypes.indexOf(row)
      this.currSelVehicleTypes.splice(index, 1)
    }
  }
})
