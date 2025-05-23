<template>


	<view style="padding: 10px;">
		<view class="gui-margin-top">
			<text class="gui-h5 gui-color-gray gui-bold">建议反馈</text>
		</view>
		<form @submit="submit2">
			<!-- <view class="gui-margin-top">
						<text class="gui-text-small gui-color-gray">小字 Label</text>
					</view> -->
			<view style="margin-top:8rpx;" class="gui-margin-to">
				<textarea v-model="data.content" name="name" placeholder="说点什么..."
					class="gui-textarea gui-bg-gray gui-border-box" style="width:690rpx;" />
			</view>
			<view class="gui-flex gui-rows gui-nowrap gui-space-between gui-align-items-center"
				style="margin-top:20rpx;">
				<view class="">
				</view>
				<view class="">
					<gui-submit-button ref="guibtn2" titleClass="mygui-sbutton-text" baseClass="mygui-sbutton"
						loaingPointClass="mygui-sbutton-loading-point" defaultClass="mygui-sbutton-default"
						loadingClass="mygui-sbutton-loading" successClass="mygui-sbutton-success">
						<text class="gui-block-text mygui-sbutton-text gui-icons" slot="default">&#xe6b8; 提交</text>
						<!-- 此处真实按钮透明度为 0, 可以利用样式将其设置的大一些（大于按钮组件本身尺寸），更便于点击 -->
						<button type="default" style="width:300rpx; height:200rpx;" slot="realBtn"
							form-type="submit">提交</button>
						<text class="gui-block-text mygui-sbutton-text gui-icons" slot="success">&#xe7f8; 提交成功</text>
						<text class="gui-block-text mygui-sbutton-text gui-icons" slot="error">&#xe646; 提交失败</text>
					</gui-submit-button>
				</view>
			</view>
		</form>
	</view>
	</view>
</template>

<script>
	var graceJS = require('@/GraceUI5/js/grace.js');
	import request from '../../../GraceUI5/js/request/request'
	export default {
		data() {
			return {
				data: {
					content: null,
					userId: null,
					userName: null
				}
			}
		},
		methods: {
			submit2: function(e) {
				// 检查按钮状态 按钮状态不为 1 阻止重复提交
				var btnStatus = this.$refs.guibtn2.status;
				if (btnStatus != 1) {
					return;
				}
				this.$refs.guibtn2.loading();
				// 提交
				console.log(e);
				// 模拟 api 请求, 使用箭头函数保持 this
				
				this.data.userId = graceJS.getStorage("id")
				this.data.userName = graceJS.getStorage("name")
				uni.gRequest.post("bohe/addJianyi", {
					data: this.data
				}, (res) => {
					console.log(res.data)
					if (res.data.code == 200) {
						uni.showToast({
							title: "提交成功",
							icon: "success"
						});
						this.$refs.guibtn2.success();


					} else {
						uni.showToast({
							title: "提交失败",
							icon: "none"
						});
						this.$refs.guibtn2.fail();

					}
					setTimeout(() => {
						// 重置按钮
						this.$refs.guibtn2.reset();
					}, 1500);
				})

			}
		}
	}
</script>

<style>

</style>