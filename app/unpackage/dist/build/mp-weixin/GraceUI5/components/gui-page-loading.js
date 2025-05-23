(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["GraceUI5/components/gui-page-loading"],{"037d":function(n,t,e){"use strict";var i=e("2fc8"),u=e.n(i);u.a},"09b9":function(n,t,e){"use strict";e.r(t);var i=e("b4cc"),u=e.n(i);for(var a in i)["default"].indexOf(a)<0&&function(n){e.d(t,n,(function(){return i[n]}))}(a);t["default"]=u.a},"2fc8":function(n,t,e){},6100:function(n,t,e){"use strict";e.r(t);var i=e("ce75"),u=e("09b9");for(var a in u)["default"].indexOf(a)<0&&function(n){e.d(t,n,(function(){return u[n]}))}(a);e("037d");var o=e("f0c5"),r=Object(o["a"])(u["default"],i["b"],i["c"],!1,null,"9fb0adb2",null,!1,i["a"],void 0);t["default"]=r.exports},b4cc:function(n,t,e){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var i={name:"gui-page-loading",props:{},data:function(){return{isLoading:!1,BindingXObjs:[null,null,null],AnimateObjs:[null,null,null],animateTimer:800,intervalID:null}},watch:{},methods:{stopfun:function(n){return n.stopPropagation(),null},open:function(){this.isLoading=!0},close:function(){var n=this;setTimeout((function(){n.isLoading=!1}),100)},getRefs:function(n,t,e){var i=this;if(t>=50)return e(this.$refs[n]),!1;var u=this.$refs[n];if(u){if(u._data)return void e(u)}else t++,setTimeout((function(){i.getRefs(n,t,e)}),100)}}};t.default=i},ce75:function(n,t,e){"use strict";e.d(t,"b",(function(){return i})),e.d(t,"c",(function(){return u})),e.d(t,"a",(function(){}));var i=function(){var n=this.$createElement;this._self._c},u=[]}}]);
;(global["webpackJsonp"] = global["webpackJsonp"] || []).push([
    'GraceUI5/components/gui-page-loading-create-component',
    {
        'GraceUI5/components/gui-page-loading-create-component':(function(module, exports, __webpack_require__){
            __webpack_require__('543d')['createComponent'](__webpack_require__("6100"))
        })
    },
    [['GraceUI5/components/gui-page-loading-create-component']]
]);
