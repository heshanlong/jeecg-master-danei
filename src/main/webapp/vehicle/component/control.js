document.write('<link rel="stylesheet" href="./css/control.css"/>')
Vue.component('control', {
  template: `
    <p class="control">
        <button @click="$emit('refresh')"><i class="glyphicon glyphicon-refresh"></i></button>
        <button @click="$emit('change-style')"><i class="glyphicon glyphicon-list-alt"></i></button>
        <button @click.stop="showList=!showList">
            <i class="glyphicon glyphicon-th"></i>
            <span class="caret"></span>
            <ul class="headerList" v-show="showList">
                <li v-for="(item, index) in headerList" :key="index">
                    <label><input type="checkbox" @change="$emit('change-header')" v-model="item.show">{{ item.label }}</label>
                </li>
            </ul>
        </button>
    </p>
  `,
  props: ['headerList'],
  data () {
    return {
      showList: false,
    }
  },
  methods: {
    hiddenList () {
      this.showList = false
    }
  }
})
