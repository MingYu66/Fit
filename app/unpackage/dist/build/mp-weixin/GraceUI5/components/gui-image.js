(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["GraceUI5/components/gui-image"],{"1dab":function(t,i,e){"use strict";e.r(i);var n=e("ca0e"),a=e("9720");for(var u in a)["default"].indexOf(u)<0&&function(t){e.d(i,t,(function(){return a[t]}))}(u);e("9607");var r=e("f0c5"),d=Object(r["a"])(a["default"],n["b"],n["c"],!1,null,"2a92a26b",null,!1,n["a"],void 0);i["default"]=d.exports},7077:function(t,i,e){},9607:function(t,i,e){"use strict";var n=e("7077"),a=e.n(n);a.a},9720:function(t,i,e){"use strict";e.r(i);var n=e("a643"),a=e.n(n);for(var u in n)["default"].indexOf(u)<0&&function(t){e.d(i,t,(function(){return n[t]}))}(u);i["default"]=a.a},a643:function(t,i,e){"use strict";Object.defineProperty(i,"__esModule",{value:!0}),i.default=void 0;var n={name:"gui-image",props:{src:{type:String,default:""},width:{type:Number,default:300},height:{type:Number,default:0},timer:{type:Number,default:200},borderRadius:{type:String,default:"0rpx"},mode:{type:String,default:"widthFix"}},data:function(){return{isLoading:!0,imgHeight:180,opacity:0,animate:!1,failed:!1}},methods:{imgLoad:function(t){var i=this,e=t.detail.width/t.detail.height;"widthFix"==this.mode?this.imgHeight=this.width/e:this.imgHeight=this.height,this.animate=!0,setTimeout((function(){i.isLoading=!1,i.opacity=1}),this.timer)},error:function(){this.isLoading=!1,this.failed=!0}}};i.default=n},ca0e:function(t,i,e){"use strict";e.d(i,"b",(function(){return n})),e.d(i,"c",(function(){return a})),e.d(i,"a",(function(){}));var n=function(){var t=this.$createElement;this._self._c},a=[]}}]);
;(global["webpackJsonp"] = global["webpackJsonp"] || []).push([
    'GraceUI5/components/gui-image-create-component',
    {
        'GraceUI5/components/gui-image-create-component':(function(module, exports, __webpack_require__){
            __webpack_require__('543d')['createComponent'](__webpack_require__("1dab"))
        })
    },
    [['GraceUI5/components/gui-image-create-component']]
]);
