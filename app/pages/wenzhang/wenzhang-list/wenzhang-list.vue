<template>
	<!--
	 本页面模板教程：https://ext.dcloud.net.cn/plugin?id=2587
	 uni-list 文档：https://ext.dcloud.net.cn/plugin?id=24
	 uniCloud 文档：https://uniapp.dcloud.io/uniCloud/README
	 unicloud-db 组件文档：https://uniapp.dcloud.io/uniCloud/unicloud-db
	 DB Schema 规范：https://uniapp.dcloud.net.cn/uniCloud/schema
	 -->
	<view>
		<gui-page :customHeader="true" :customFooter="true" iphoneXButtomStyle="background:#F8F8F8"
			:footerSets="{height:100, zIndex:100, bg:'none'}" style="background-color: #f6f7fa;">
			<view slot="gHeader"><gui-demo-header></gui-demo-header></view>
		
			<view slot="gBody" class="gui-margin" style="padding-bottom:50rpx;margin-top: 10px; ">
	
			<!-- 基于 uni-list 的页面布局 -->
			<uni-list>
				<!-- to属性：将新闻ID和标题传给详情页 -->
				<!-- TODO：需增加日期格式化的示例 -->
				<uni-list-item direction="row" v-for="item in data" :key="item.id" :title="item.title" :to="'/pages/wenzhang/detail/detail?id='+item.id+'&title='+item.title">
					<!-- 通过body插槽定义列表内容显示 -->
					<template v-slot:body>
						<view class="uni-list-box uni-content">
							<view class="uni-title uni-ellipsis-2">{{item.title}}</view>
							<view class="uni-note">
								<!-- <text>{{item.user_name}}</text> -->
								<text>{{item.createTime}}</text>
							</view>
						</view>
					</template>
					<!-- 通过footer插槽自定义图片右侧显示 -->
					<template v-slot:footer>
						<view class="uni-thumb" style="margin: 0;">
							<!-- 当前判断长度只为简单判断类型，实际业务中，根据逻辑直接渲染即可 -->
							<image :src="'http://192.168.3.4:8081' + item.img" mode="aspectFill"></image>
						</view>
					</template>
				</uni-list-item>
			</uni-list>
		
	</view>
		<!-- 自定义底部导航 -->
		<!-- 底部插槽实现 -->
		<!-- 边框使用 style 实现，可以根据自己项目实际情况修改 -->
		<view slot="gFooter" class="gui-flex gui-rows gui-nowrap gui-space-between gui-align-items-end"
			style="height:150rpx;">
			<view class="gui-footer-icon-buttons" @tap="navChang(0)">
				<text class="gui-footer-icon-buttons-icon gui-block-text gui-icons"
					:class="[navCurrent == 0 ? 'gui-nav-bottom-active-color' : 'gui-nav-bottom-color']">&#xe63b;</text>
				<text class="gui-footer-icon-buttons-text gui-block-text gui-nav-bottom-color"
					:class="[navCurrent == 0 ? 'gui-nav-bottom-active-color' : 'gui-nav-bottom-color']">首页</text>
			</view>
			<view class="gui-footer-icon-buttons" @tap="navChang(1)">
				<text class="gui-footer-icon-buttons-icon gui-block-text zzduoer"
					:class="[navCurrent == 1 ? 'gui-nav-bottom-active-color' : 'gui-nav-bottom-color']">&#xe715;</text>
				<text class="gui-footer-icon-buttons-text gui-block-text gui-nav-bottom-color"
					:class="[navCurrent == 1 ? 'gui-nav-bottom-active-color' : 'gui-nav-bottom-color']">新闻</text>
			</view>
			<!-- 凸出按钮占位  普通按钮 实现下面的 gui-footer-icon-buttons 即可 -->
			<view class="gui-footer-icon-buttons"></view>
			<view class="gui-footer-icon-buttons" @tap="navChang(3)">
				<text class="gui-footer-icon-buttons-icon gui-block-text zzduoer"
					:class="[navCurrent == 3 ? 'gui-nav-bottom-active-color' : 'gui-nav-bottom-color']">&#xe629;</text>
				<text class="gui-footer-icon-buttons-text gui-block-text"
					:class="[navCurrent == 3 ? 'gui-nav-bottom-active-color' : 'gui-nav-bottom-color']">交流</text>
			</view>
			<view class="gui-footer-icon-buttons" @tap="navChang(4)">
				<text class="gui-footer-icon-buttons-icon gui-block-text zzduoer"
					:class="[navCurrent == 4 ? 'gui-nav-bottom-active-color' : 'gui-nav-bottom-color']">&#xe606;</text>
				<text class="gui-footer-icon-buttons-text gui-block-text"
					:class="[navCurrent == 4 ? 'gui-nav-bottom-active-color' : 'gui-nav-bottom-color']">我的</text>
			</view>
			<!-- 使用定位方式实现凸出按钮 -->
			<view class="gui-footer-buttons-ab" @tap="navChang(2)">
				<text class="gui-footer-buttons-ab-icon gui-block-text   zzduoer"
					:class="[navCurrent == 2 ? 'gui-nav-bottom-active-color' : 'gui-nav-bottom-color']">&#xe654;</text>
				<text class="gui-footer-icon-buttons-text gui-block-text"
					:class="[navCurrent == 2 ? 'gui-nav-bottom-active-color' : 'gui-nav-bottom-color']">打卡</text>
			</view>
		</view>
	</gui-page>
	</view>
</template>

<script>
	export default {
		components: {},
		data() {
			return {
				
				formData: {
					status: 'loading', // 加载状态
				},
				tipShow: false ,// 是否显示顶部提示框
				data:[]
			};
		},
		onLoad() {
			uni.gRequest.post("bohe/getWenZhang", {
				
			}, (res) => {
				console.log(res.data)
				this.data = res.data.data
			})
		},
		methods: {
			load(data, ended) {
				if (ended) {
					this.formData.status = 'noMore'
				}
			},
			navChang: function(index) {
				console.log(index)
				if (index == 0) {
					uni.switchTab({
						url: "../../switchPages/index"
					})
				} else if (index == 1) {
					
				} else if (index == 2) {
					uni.switchTab({
						url: "../../switchPages/index"
					})
				} else if (index == 3) {
					uni.switchTab({
						url: "../../uSpeak/uSpeak",
						fail(err) {
							console.log(err)
						}
					})
				} else if (index == 4) {
					uni.switchTab({
						url: "../../switchPages/about"
					})
				}
				this.navCurrent = index;
			
			},
		},
		
	};
</script>

<style lang="scss">

	@import '@/common/uni-ui.scss';

	page {
		display: flex;
		flex-direction: column;
		box-sizing: border-box;
		background-color: #efeff4;
		min-height: 100%;
		height: auto;
	}

	.uni-list-box {
		margin-top: 0;
	}

	.uni-content {
		padding-right: 10px;
	}

	.uni-note {
		display: flex;
		margin: 0;
		justify-content: space-between;
	}

	.tips {
		color: #67c23a;
		font-size: 14px;
		line-height: 40px;
		text-align: center;
		background-color: #f0f9eb;
		height: 0;
		opacity: 0;
		transform: translateY(-100%);
		transition: all 0.3s;
	}

	.tips-ani {
		transform: translateY(0);
		height: 40px;
		opacity: 1;
	}

	.content {
		width: 100%;
		display: flex;
	}

	.list-picture {
		width: 100%;
		height: 145px;
	}

	.thumb-image {
		width: 100%;
		height: 100%;
	}

	.ellipsis {
		display: flex;
		overflow: hidden;
	}

	.uni-ellipsis-1 {
		overflow: hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
	}

	.uni-ellipsis-2 {
		overflow: hidden;
		text-overflow: ellipsis;
		display: -webkit-box;
		-webkit-line-clamp: 2;
		-webkit-box-orient: vertical;
	}

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
	/* #endif */
	
	
	.gui-footer-icon-buttons {
		width: 130rpx;
		height: 80rpx;
		padding: 10rpx;
		flex: 1;
		position: relative;
		margin: 0;
		background-color: #F8F8F8;
	}
	
	.gui-footer-buttons-ab {
		position: absolute;
		z-index: 101;
		left: 325rpx;
		top: 0rpx;
		width: 110rpx;
		height: 150rpx;
	}
	
	.gui-footer-buttons-ab-icon {
		width: 110rpx;
		height: 110rpx;
		line-height: 120rpx;
		text-align: center;
		font-size: 68rpx;
		background-color: #F7F8FA;
		border-radius: 100rpx;
	}
</style>
