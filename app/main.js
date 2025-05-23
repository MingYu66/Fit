import Vue from 'vue'
import App from './App'
import VueCompositionAPI from '@vue/composition-api'
// import quill from "quill";
// window.Quill = quill;
Vue.use(VueCompositionAPI)
Vue.config.productionTip = false
App.mpType = 'app'
/* 全局挂载请求库 */
import GraceRequest from './GraceUI5/js/request/request.js'
var GraceRequestConfig = require('@/GraceUI5/js/request/config.js');
uni.gRequest = GraceRequest;
Vue.config.silent = true
const app = new Vue({
    ...App
})
app.$mount()