<script>
export default {
	onLaunch: function() {
		uni.hideTabBar() 
		// #ifdef APP-PLUS
		plus.screen.lockOrientation('portrait-primary'); //锁定屏幕
		const dom = weex.requireModule('dom');
		dom.addRule('fontFace', { 
			'fontFamily': "graceIconfont",
			'src': "url('/static/grace.ttf')"
		});
		dom.addRule('fontFace', {
			'fontFamily': "mint",
			'src': "url('/static/mint.ttf')"
		});
		dom.addRule('fontFace', {
			'fontFamily': "zzduoer",
			'src': "url('/static/zzduoer.ttf')"
		});
		dom.addRule('fontFace', {
			'fontFamily': "aknngsa",
			'src': "url('/static/aknngsa.ttf')"
		});
		
		dom.addRule('fontFace', {
			'fontFamily': "dizhidaxue",
			'src': "url('/static/dizhidaxue.ttf')"
		});
		
		
		// #endif
	},
	onShow: function() {},
	onHide: function() {}
}
</script>
<style>
/* 加载框架核心样式 */
@import "./GraceUI5/css/graceUI.css";
/* 加载主题样式 */
@import "./GraceUI5/skin/black.css";
/* 加载图标字体 - 条件编译模式 */
/* #ifdef APP-PLUS-NVUE */
.gui-icons{font-family:graceIconfont;}
/* #endif */
@keyframes progross1 {
    to {
        transform: rotate(360deg);
    }
}
@keyframes progross2 {
    to {
        transform: rotate(0deg);
    }
}

/* 自定义图标 */
/* #ifndef APP-NVUE */
@font-face{
	font-family : "mint";
	font-weight : normal;
	font-style  : normal; 
	src         : url('@/static/mint.ttf') format('truetype');
}
.mint{font-family:"mint"; font-style:normal;}
@font-face{
	font-family : "zzduoer";
	font-weight : normal;
	font-style  : normal; 
	src         : url('@/static/zzduoer.ttf') format('truetype');
}
@font-face{
	font-family : "aknngsa";
	font-weight : normal;
	font-style  : normal; 
	src         : url('@/static/aknngsa.ttf') format('truetype');
}
@font-face{
	font-family : "dizhidaxue";
	font-weight : normal;
	font-style  : normal; 
	src         : url('@/static/dizhidaxue.ttf') format('truetype');
}

.dizhidaxue{font-family:"dizhidaxue"; font-style:normal;}
.aknngsa{font-family:"aknngsa"; font-style:normal;}
.zzduoer{font-family:"zzduoer"; font-style:normal;}
/* #endif */
</style>
