<template>
	<view>
		<gui-page :fullPage="true" :isLoading="pageLoading" ref="guiPage">
			<view slot="gBody" class="gui-flex1">
				<!-- 搜索框 -->
				<view class="header-search gui-bg-gray gui-border-box">
					<gui-search @clear="search" @confirm="search"></gui-search>
				</view>
				<!-- 主体区域 -->
				<view class="gui-flex gui-rows gui-space-between">
					<!-- 左侧分类列表 -->
					<scroll-view :scroll-y="true" :show-scrollbar="false" :scroll-with-animation="true"
						:scroll-into-view="leftTo" class="gui-cate-left" :style="{height:mainHeight+'px'}">
						<view class="gui-cate-left-item gui-border-box" v-for="(item, index) in mainCate" :key="index"
							:class="[currentCateIndex == item.id ? 'gui-cate-left-current' : '']" :id="'cate'+item.id">
							<text class="gui-border-l"
								:class="['gui-cate-left-item-title','gui-block-text', currentCateIndex == item.id ? 'gui-cate-left-current' : '']"
								:data-cateid="item.id" @tap="changCate">{{item.name}}</text>
						</view>
					</scroll-view>
					<!-- 右侧列表 -->
					<scroll-view :scroll-into-view="productListTo" :show-scrollbar="false" :scroll-with-animation="true"
						:scroll-y="true" class="gui-cate-right" :style="{height:mainHeight+'px'}" @scroll="rscroll">
						<!-- 循环展示分类及分类对应的产品列表 -->
						<view class="gui-margin-top" :id="'productList'+cate.id" v-for="(cate, idx) in mainCate"
							:key="idx">
							<text
								class="gui-block-text gui-h6 gui-bold gui-color-gray gui-cate-right-title">{{cate.name}}</text>
							<!-- 循环展示产品 -->
							<view class="gui-flex gui-rows gui-nowrap gui-cate-product-list gui-align-items-center"
								v-for="(product, pidx) in cate.products" :key="pidx" :id="'products'+product.id"
								:data-parentid="cate.id"  @tap="open1(product)">
								<view class="gui-list-image gui-relative">
									<image class="gui-list-image" :src="product.thumbImageUrl"></image>

								</view>
								<view class="gui-list-body gui-border-b">
									<view class="gui-list-title">

										<text class="gui-list-title-text gui-primary-color">{{product.name}}</text>

									</view>

									<text class="gui-list-body-desc gui-color-gray"><text
											class="gui-color-red">{{product.calory}}</text>
										千卡/{{product.weight}}克</text>
								</view>
								<text class="gui-list-arrow-right gui-icons gui-color-red gui-bg-green"
									v-if="product.healthLight == 1"
									style="border-radius: 100%;width: 20upx;height:20upx;"></text>
								<text class="gui-list-arrow-right gui-icons gui-color-red gui-bg-yellow"
									v-if="product.healthLight == 2"
									style="border-radius: 100%;width: 20upx;height:20upx;"></text>
								<text class="gui-list-arrow-right gui-icons gui-color-red gui-bg-red"
									v-if="product.healthLight == 3"
									style="border-radius: 100%;width: 20upx;height:20upx;"></text>
								<text class="gui-list-arrow-right gui-icons gui-color-red gui-bg-black"
									v-if="product.healthLight == 4"
									style="border-radius: 100%;width: 20upx;height:20upx;"></text>

								<!-- <view class="gui-cate-pimg">
								<gui-image :src="product.thumbImageUrl" :width="150" :height="150" borderRadius="10rpx"></gui-image>
							</view>
							<view class="gui-cate-pbody">
								<text class="gui-text gui-primary-color gui-block-text">{{product.name}} · {{product.id}}</text>
								<view class="gui-flex gui-rows gui-nowrap gui-space-between">
									<text class="gui-cate-price gui-color-red">{{product.calory}}<text class="gui-color-gray">千卡/100g</text></text>
								</view>
								<view class="gui-flex gui-rows gui-nowrap gui-space-between">
									<text></text>
									<text class="gui-icons gui-text-small gui-color-gray">Button</text>
								</view>
							</view> -->


							</view>
						</view>
					</scroll-view>
				</view>
			</view>



		</gui-page>
		
		<gui-modal ref="guimodal1" 
				:title=" food.createTime +' '+ food.name" >
					<view slot="content" style="height: 200px;" class="gui-padding gui-bg-gray">
						<view class="gui-flex gui-rows gui-nowrap gui-cate-product-list gui-align-items-center">
						
							<view class="gui-list-image gui-relative">
								<image class="gui-list-image" :src="food.thumbImageUrl"></image>
							
							</view>
							<view class="gui-list-body gui-border-b">
								<view class="gui-list-title">
							
									<text class="gui-list-title-text gui-primary-color">{{food.name}}</text>
							
								</view>
							
								<text class="gui-list-body-desc gui-color-gray"><text
										class="gui-color-red">{{food.calory}}</text>
									千卡/{{food.weight}}克</text>
							</view>
							<uni-data-select
							     v-model="type"
							     :localdata="selectMenu2"
							   ></uni-data-select>
						</view>
						
						<view class="gui-flex gui-align-items-center gui-justify-content-start">
							<view class="gui-color-gray gui-flex gui-text-small" style="width: 30%;">{{food.calory * form.value / food.weight}} 千卡</view>
							<view class="gui-text-center">
								<input type="number" name="value" v-model="form.value"  id="">
							</view>
							<view class="gui-color-gray  gui-text-small" style="width: 30%;">
								估算重量
							</view>
						</view>
					</view>
					<!-- 利用 flex 布局 可以放置多个自定义按钮哦  -->
					<view slot="btns" class="gui-flex gui-rows gui-space-between">
						<view class="modal-btns gui-flex1" style="margin-right:80rpx;">
							<text class="modal-btns gui-color-gray" @tap="close1">取消</text>
						</view>
						<view class="modal-btns gui-flex1" style="margin-left:80rpx;">
							<text class="modal-btns gui-color-blue" @tap="confirm1">添加</text>
						</view>
					</view>
				</gui-modal>
		
		
	</view>
	<!-- 底部弹出 -->


</template>
<script>
	var graceJS = require('@/GraceUI5/js/grace.js');
	var cateChangeData = require('@/GraceUI5/demoData/cateChange.js');
	import guipopup from "@/GraceUI5/components/gui-popup.vue";
import grace from "@/GraceUI5/js/grace.js";
	export default {
		components: {
			guipopup
		},
		data() {
			return {
				img: "https://images.unsplash.com/photo-1660505465468-c898ea7ff674?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDQ2fHhqUFI0aGxrQkdBfHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=200&q=90",
				// 全屏加载，当数据请求完毕时关闭
				pageLoading: true,
				// 核心区域高度
				mainHeight: 300,
				// 左侧分类定位
				leftTo: 'cate',
				// 标识切换时左侧点击触发的
				isLeftTap: false,
				// 左侧分类数据
				mainCate: cateChangeData.products,
				// 当前分类
				currentCateIndex: 1,
				// 延迟执行防止卡顿
				scrollTimer: null,
				// 产品列表滚动定位
				productListTo: '',
				food:{
					
				},
				form:{
					value:100
				},
				selectMenu2: [
					{
					value:0,
					text:"早餐"
				},
				{
					value:1,
					text:"午餐"
				},
				{
					value:2,
					text:"晚餐"
				},
				{
					value:3,
					text:"加餐"
				},
				],
			}
		},
		onLoad: function(option) {
			console.log(option.type)
			this.type = option.type
			uni.gRequest.post("bohe/categorisAndFood", {}, (res) => {
				if (res.data.code == 200) {
					this.mainCate = res.data.data
				}

			})
			console.log(this.mainCate)
			// 获取页面主体高度从而得出主体高度
			graceJS.getRefs('guiPage', this, 0, (ref) => {
				ref.getDomSize('guiPageBody', (e) => {
					// 02. 导航的高度
					// 可以 使用 graceJS.select() 函数动态获取， 也可以使用 uni.upx2px 转换已知高度
					this.mainHeight = e.height - uni.upx2px(110);
					setTimeout(() => {
						this.pageLoading = false;

					}, 1000)
				});
			});

		},
		methods: {
			open1 : function (e) {
				var date = new Date();
				

				this.food = e
				this.food.createTime =  date.getFullYear()+'-'+(date.getMonth() + 1)+'-'+date.getDate()
				
						this.$refs.guimodal1.open();
					},
					close1 : function () {
						this.$refs.guimodal1.close();
					},
					confirm1 : function () {
						console.log(graceJS.getStorage("id"))
						console.log(this.type)
						console.log(this.form)
						console.log(this.food)
						console.log(this.food.calory * this.form.value / this.food.weight)
						var params = {
							cType:this.type,
							cValue:this.food.calory * this.form.value / this.food.weight,
							cName:this.food.name,
							cId:this.food.id,
							cImg:this.food.thumbImageUrl,
							uid:graceJS.getStorage("id"),
							cNum:this.form.value,
							cWeight:this.food.weight,
							createTime:this.food.createTime
						}
						console.log(params)
						uni.gRequest.post("bohe/addCalory", {
							data: params
						}, (res) => {
							console.log(res.data)
							if (res.data.code == 200) {
								
							}
						
						})
						// 客户点击确认按钮后的逻辑请在此处实现
						this.$refs.guimodal1.close();
					},
		
		
			changCate: function(e) {
				this.isLeftTap = true;
				var cateid = e.currentTarget.dataset.cateid;
				this.currentCateIndex = cateid;
				if (cateid == 1) {
					this.productListTo = 'productList2';
					this.$nextTick(() => {
						this.productListTo = 'productList' + cateid;
					});
				} else {
					this.productListTo = 'productList' + cateid;
				}
				setTimeout(() => {
					this.isLeftTap = false;
				}, 3000)

			},
			rscroll: function(e) {
				if (this.isLeftTap) {
					return;
				}
				if (this.scrollTimer != null) {
					clearTimeout(this.scrollTimer);
				}
				this.scrollTimer = setTimeout(() => {
					this.getIndex();
				}, 100);
			},
			// 动态识别分类激活
			getIndex: function(i) {
				const query = uni.createSelectorQuery()
				query.selectAll('.gui-cate-product-list').boundingClientRect()
				query.selectViewport().scrollOffset()
				query.exec((res) => {
					var products = res[0];
					for (let i = 0; i < products.length; i++) {
						if (products[i].top > 0 && products[i].top >= this.mainHeight / 2) {
							this.currentCateIndex = products[i].dataset.parentid,
								this.leftTo = 'cate' + products[i].dataset.parentid;
							break;
						}
					}
					console.log(this.leftTo);
				});
			},
			// 搜索
			search: function(e) {
				var kwd = e;
				var searchIndex = -1;
				var index = 0;
				for (var k in this.mainCate) {
					for (var i = 0; i < this.mainCate[k].products.length; i++) {
						if (this.mainCate[k].products[i].name.indexOf(kwd) != -1) {
							searchIndex = this.mainCate[k].products[i].id;
							break;
						}
						index++;
					}
					if (searchIndex != -1) {
						break;
					}
				}
				if (searchIndex != -1) {
					this.productListTo = 'products' + searchIndex;
				}
			}
		}
	}
</script>
<style>
	.demo-close {
		width: 100rpx;
		height: 100rpx;
		line-height: 100rpx;
		opacity: 0.88;
		text-align: center;
		font-size: 58rpx;
	}

	.demo-lr {
		flex: 1;
		height: 200rpx;
	}

	.demo-lr-items {
		height: 120rpx;
		line-height: 120rpx;
		text-align: center;
		overflow: hidden;
	}

	.header-search {
		padding: 20rpx 80rpx;
		width: 750rpx;
	}

	.gui-cate-left {
		width: 200rpx;
		background-color: #F6F7F8;
	}

	.gui-cate-right {
		width: 520rpx;
		overflow: hidden;
	}

	.gui-cate-left-item {
		height: 100rpx;
		padding: 35rpx 0;
		font-size: 26rpx;
	}

	.gui-cate-left-item-title {
		line-height: 32rpx;
		font-size: 28rpx;
		border-color: #F6F7F8;
		border-left-width: 8rpx;
		text-align: center;
	}

	.gui-cate-left-current {
		border-color: #FF0066 !important;
		background-color: #FFFFFF;
		color: #FF0066;
		font-weight: bold;
	}

	.gui-cate-right-title {
		line-height: 80rpx;
	}

	.gui-cate-product-list {
		margin-bottom: 30rpx;
		margin-right: 25rpx;
	}

	.gui-cate-pimg {
		width: 150rpx;
	}

	.gui-cate-pbody {
		margin-left: 30rpx;
		width: 100rpx;
		flex: 1;
		padding-top: -20rpx;
	}

	.gui-cate-price {
		font-size: 32rpx;
		line-height: 60rpx;
	}
	.modal-btns{line-height:88rpx; font-size:26rpx; text-align:center; width:200rpx;}

</style>