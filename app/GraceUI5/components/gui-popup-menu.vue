<template>
	<view class="gui-relative" v-if="showIn">
		<view class="grace-popup-mask"
		:class="[showIn?'grace-shade-in':'', outting ? 'grace-shade-out' : '']" 
		@tap.stop="close" @touchmove.stop.prevent="stopfun"
		:style="{backgroundColor:background, zIndex:zIndex}" 
		v-if="showIn"></view>
		<view class="grace-popup-menu" 
		v-if="showIn" ref="guipopupmenu" 
		:class="[showIn?'grace-shade-in':'', outting ? 'grace-shade-out' : '']" 
		:style="{
		top:top+'px', right:right+'px', width:menuWidth, 
		zIndex:zIndex+1, height:showIn?'':'0rpx'
		}">
			<!-- #ifndef APP-NVUE -->
			<view :class="['gui-flex', 'gui-rows', 'arrow-d-'+arrowDirection]" 
			v-if="isArrow">
				<view class="arrow-up" :style="{margin:arrowMargin, borderBottomColor:arrowColor}"></view>
			</view>
			<!-- #endif -->
			<view :style="{width:menuWidth}">
				<slot></slot>
			</view>
		</view>
	</view>
</template>
<script>
// #ifdef APP-NVUE
const animation = weex.requireModule('animation');
// #endif
var graceJS = require('@/GraceUI5/js/grace.js');
export default {
	name  : "gui-popup-menu",
	props : {
		menuWidth    :  { type : String,  default : '258rpx' },
		background   :  { type : String,  default : 'rgba(0,0,0, 0.3)' },
		zIndex       :  { type : Number,  default : 99 },
		isArrow      :  { type : Boolean, default : true},
		arrowDirection : {type : String,  default : "right"},
		arrowMargin  :  { type : String, default : "0 15rpx"},
		arrowColor  :  { type : String, default : "#FFFFFF"}
	},
	data() {
		return {
			showIn  : false,
			top     : 0,
			right   : 0,
			outting : false
		}
	},
	methods: {
		stopfun  : function(e){e.stopPropagation(); return null;},
		open     : function(){
			this.outting = false;
			this.showIn  = true;
			// #ifdef APP-NVUE
			graceJS.getRefs('guipopupmenu', this, 0, (guipopupref)=>{
				animation.transition(guipopupref, {
					styles: {opacity:1, transform:'scale(1)'},
					duration: 200, //ms
					timingFunction: 'ease',
					delay: 0 //ms
				});
			});
			// #endif
		},
		close     : function(){
			this.outting    = true;
			setTimeout(()=>{
				this.showIn = false;
			},200);
			// #ifdef APP-NVUE
			graceJS.getRefs('guipopupmenu', this, 0, (guipopupref)=>{
				animation.transition(guipopupref, {
					styles: {opacity:0, transform:'scale(0.5)'},
					duration: 200, //ms
					timingFunction: 'ease',
					delay: 0 //ms
				});
			});
			// #endif
		},
		setTop   : function (top) {
			this.top = top;
		},
		setRight : function (right) {
			this.right = right;
		}
	}
}
</script>
<style scoped>
.grace-popup-menu{width:258rpx; padding:10rpx; right:0; top:0; position:absolute; opacity:0; transform:scale(0.1);}
/* #ifdef APP-NVUE */
.grace-popup-menu{padding:0rpx 10rpx;}
/* #endif */
.grace-popup-mask{width:750rpx; position:fixed; left:0; top:0px; bottom:0; flex:1;}
/* #ifndef APP-NVUE */
.grace-popup-mask{height:100%;}
.arrow-up{width:0; height:0; border-left:18rpx solid transparent; border-right:18rpx solid transparent; border-bottom:18rpx solid #FFFFFF;}
.arrow-d-right{justify-content:flex-end;}
.arrow-d-center{justify-content:center;}
.arrow-d-left{justify-content:flex-start;}
.grace-shade-in{animation:grace-shade-in-a 150ms ease-in forwards;}
@keyframes grace-shade-in-a{0%{transform:scale(0.1); opacity:0;} 100%{transform: scale(1); opacity:1;}}
.grace-shade-out{animation:grace-shade-out-a 150ms ease-out forwards;}
@keyframes grace-shade-out-a{0%{transform:scale(1); opacity:1;} 100%{transform: scale(0.5); opacity:0;}}
/* #endif */
/* #ifdef APP-NVUE */
.grace-popup-menu{position:fixed;}
/* #endif */
</style>