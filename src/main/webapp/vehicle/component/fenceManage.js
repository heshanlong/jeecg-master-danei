document.write('<link rel="stylesheet" href="./css/fenceManage.css"/>')
document.write('<script src="./js/mapTool.js"></script>')
Vue.component('fence-manage', {
  template: `
    <div class="fenceManage">
      <p class="tool">
        <span>{{ title }}：</span>
        <el-input v-model="fenceName" clearable></el-input>
        <button class="normal" @click="getData"><i class="glyphicon glyphicon-search"></i> 查询</button>
        <button class="remove" @click="clear"><i class="glyphicon glyphicon-clear"></i> 清除</button>
        <template v-if="type">
          <button class="normal" @click="draw(0)">矩形</button>
          <button class="normal" @click="draw(1)">多边形</button>
        </template>
      </p>
      <div>
        <div class="list">
          <el-table :data="dataSource" height="100%" border @row-dblclick="selectFence">
            <el-table-column align="center" :label="title" :prop="prop"></el-table-column>
            <el-table-column align="center" label="操作" #default="{row}">
              <button class="other mini" @click="viewFence(row)">查看</button>
              <button class="remove mini" v-if="type" @click="deleteFence(row)">删除</button>
            </el-table-column>
          </el-table>
        </div>
        <div class="map" id="fenceMap"></div>
      </div>
      <el-dialog title="保存区域" :close-on-click-modal="false" class="saveDialog" :modal="false" :visible.sync="showSave" top="30vh">
        <span>区域名称：</span><el-input v-model="areaName" v-focus></el-input>
        <p class="operate">
            <button class="sure" @click="saveArea">确定</button>
            <button @click="cancelSave">取消</button>
        </p>
      </el-dialog>
    </div>
  `,
  props: {
    type: {
      validator: value => {
        return [0, 1].includes(value) // 0表示围栏，1表示区域
      },
      default: 0
    },
    fenceList: Array // 围栏列表
  },
  data () {
    return {
      map: '',
      vectorLayer: '',
      fenceName: '',
      areaName: '',
      dataSource: [],
      showSave: false,
      feature: {},
      points: []
    }
  },
  computed: {
    title () {
      return this.type ? '区域名称' : '围栏名称'
    },
    prop () {
      return this.type ? 'fenceName' : 'projectName'
    }
  },
  watch: {
    type (value) {
      if (value) this.getData()
      else this.dataSource = this.fenceList
    },
    fenceList (value) {
      this.dataSource = value
    }
  },
  mounted () {
    this.map = initMap('fenceMap')
    this.vectorLayer = addVectorLayer(this.map)
    if (this.type) this.getData()
    else this.dataSource = this.fenceList
  },
  methods: {
    async getData () {
      let res = await getFences({fenceName: this.fenceName})
      if (!res) {
        warning('获取数据失败')
        return
      }
      this.dataSource = res.data
      this.$emit('fence-update', res.data)
    },
    clear () {
      this.showSave = false
      this.areaName = ''
      this.vectorLayer.removeAllFeatures()
    },
    draw (type) {
      drawGeometry(this.map, this.vectorLayer, type).then(data => {
        this.showSave = true
        this.feature = data.feature
        this.points = data.coordinates
      })
    },
    async saveArea () {
      let params = {
        fenceName: this.areaName,
        graphType: 2,
        pointsStr: JSON.stringify(this.points)
      }
      await insertFence(params)
      this.cancelSave()
      this.getData()
    },
    cancelSave () {
      this.vectorLayer.removeFeatures([this.feature])
      this.showSave = false
      this.areaName = ''
    },
    selectFence (row) {
      if (!this.type) return
      this.$emit('select-fence', row.id)
    },
    viewFence (row) {
      let pointArray = row.pointArray ? row.pointArray : (row.fence ? row.fence.pointArray : [])
      addGeometry(this.map, this.vectorLayer, pointArray)
    },
    deleteFence (row) {
      delConfirm().then(async () => {
        let res = await deleteFence({id: row.id})
        if (res) {
          success((this.type ? '围栏' : '区域') + '删除成功')
          this.getData()
        }
      })
    }
  }
})
