document.write('<link rel="stylesheet" href="./css/select.css"/>')
Vue.component('vehicle-select', {
  template: `
    <div class="select">
      <el-tabs v-model="activeName">
        <el-tab-pane label="车辆列表" name="vehicleList">
          <p class="top">
            <span>
              <span>车牌：</span><el-input v-model="params.licensePlate"></el-input>
            </span>
            <span>
              <span>车辆类型：</span>
                <el-select multiple collapse-tags v-model="vehicleType" clearable>
                  <el-option-group v-for="group in vehicleTypeList" :key="group.id" :label="group.typeName">
                    <el-option v-for="item in group.items" :key="item.id" :label="item.itemName" :value="item.id"></el-option>
                  </el-option-group>
                </el-select>
            </span>
            <span>
              <span>所属单位：</span><el-input v-model="params.company"></el-input>
            </span>
            <span><button class="normal" @click="getData">查询</button></span>
          </p>
          <table-list @selected-change="selectedItems=$event" ref="tableList" :data-source="dataSource"
                      :loading="loading" :total="total" :pages="pages" :table-style="tableStyle"
                      :show-select="true" :header-list="headerList">
          </table-list>
        </el-tab-pane>
        <el-tab-pane label="已分配车辆" name="allocated">
          <el-table :data="currSelVehicles" border height="calc(100% - 10px)" empty-text="没有找到匹配的记录">
            <el-table-column label="序号" align="center" type="index"></el-table-column>
            <el-table-column label="车牌号" prop="licensePlate"></el-table-column>
            <el-table-column label="车辆类型" prop="itemName"></el-table-column>
            <el-table-column label="所属单位" prop="company"></el-table-column>
            <el-table-column label="操作" align="center" #default="{row}">
              <button class="remove mini" @click="remove(row)">删除</button>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
      <p class="btn"><button class="sure" @click="selectFinish">确定</button></p>
    </div>
  `,
  mixins: [mixin],
  props: ['selVehicles'],
  data () {
    return {
      params: {},
      activeName: 'vehicleList',
      currSelVehicles: [],
      vehicleTypeList: [],
      vehicleType: [],
      selectedItems: []
    }
  },
  mounted () {
    this.currSelVehicles = [...this.selVehicles]
    getVehicleTypes().then(result => { this.vehicleTypeList = result.data })
    this.getDataMethod = getVehicleList
    this.headerList = [
      {label: '车牌号', prop: 'licensePlate', width: 85, show: true},
      {label: '车辆类型', prop: 'itemName', width: 100, show: true},
      {label: '所属单位', prop: 'company', width: 140, show: true}
    ]
    this.getData()
  },
  watch: {
    vehicleType (value) {
      this.params.vehicleCategory = value.join(',')
    },
    selVehicles (value) {
      this.currSelVehicles = [...value]
    }
  },
  methods: {
    selectFinish () {
      let selVehicleList = []
      this.selectedItems.forEach(index => {
        selVehicleList.push(this.dataSource[index])
      })
      selVehicleList.forEach(item => {
        let res = this.currSelVehicles.find(d => d.id === item.id)
        if (!res) this.currSelVehicles.push(item)
      })
      this.$emit('select-finish', this.currSelVehicles)
    },
    reset () {
      this.activeName = 'vehicleList'
      this.$refs.tableList.clearSelect()
      this.currSelVehicles = [...this.selVehicles]
    },
    remove (row) {
      let index = this.currSelVehicles.indexOf(row)
      this.currSelVehicles.splice(index, 1)
    }
  }
})
