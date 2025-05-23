(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["GraceUI5/components/gui-header-leading"],{1164:function(e,t,n){"use strict";(function(e){Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var n={name:"gui-header-leading",props:{homePage:{type:String,default:"/pages/switchPages/index"},bgStyle:{type:String,default:""},buttonStyle:{type:String,default:""},onlyBack:{type:Boolean,default:!1},onlyHome:{type:Boolean,default:!1}},methods:{goback:function(){e.navigateBack({delta:1}),this.$emit("goback")},gohome:function(){""!=this.homePage&&e.switchTab({url:this.homePage}),this.$emit("gohome")}}};t.default=n}).call(this,n("543d")["default"])},"589f":function(e,t,n){"use strict";n.r(t);var a=n("7197"),i=n("6412");for(var o in i)["default"].indexOf(o)<0&&function(e){n.d(t,e,(function(){return i[e]}))}(o);n("67af");var u=n("f0c5"),f=Object(u["a"])(i["default"],a["b"],a["c"],!1,null,"81ab2592",null,!1,a["a"],void 0);t["default"]=f.exports},"58fe":function(e,t,n){},6412:function(e,t,n){"use strict";n.r(t);var a=n("1164"),i=n.n(a);for(var o in a)["default"].indexOf(o)<0&&function(e){n.d(t,e,(function(){return a[e]}))}(o);t["default"]=i.a},"67af":function(e,t,n){"use strict";var a=n("58fe"),i=n.n(a);i.a},7197:function(e,t,n){"use strict";n.d(t,"b",(function(){return a})),n.d(t,"c",(function(){return i})),n.d(t,"a",(function(){}));var a=function(){var e=this.$createElement;this._self._c},i=[]}}]);
;(global["webpackJsonp"] = global["webpackJsonp"] || []).push([
    'GraceUI5/components/gui-header-leading-create-component',
    {
        'GraceUI5/components/gui-header-leading-create-component':(function(module, exports, __webpack_require__){
            __webpack_require__('543d')['createComponent'](__webpack_require__("589f"))
        })
    },
    [['GraceUI5/components/gui-header-leading-create-component']]
]);
