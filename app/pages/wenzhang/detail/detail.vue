<template>
	<view>
		<gui-page :customHeader="true" :customFooter="true" iphoneXButtomStyle="background:#F8F8F8"
			:footerSets="{height:100, zIndex:100, bg:'none'}" style="background-color: #ffffff;">
			<view slot="gHeader"><gui-demo-header></gui-demo-header></view>

			<view slot="gBody" class="gui-margin" style="margin-top: 10px; ">

				<view class="card-body gui-card-body  gui-border-b card" style="padding-bottom: 20px;">
					<view class="" style="display: flex; justify-content: space-around;align-items: center;">
						<view class="btnImg" style="padding: 10px;">

							<text class="gui-h4">{{title}}</text>

						</view>
					</view>
					<text style="padding: 10px;" class="gui-h6">发布时间：{{time}}</text>
					<view style="margin-top: 10px ;">
						<view class="card-body gui-card-body gui-border-b card">
							<view class="" style="display: flex; justify-content: space-around;align-items: center;">
								<view class="btnImg" style="padding: 20px;">

									<rich-text :nodes="article"></rich-text>
								</view>
							</view>
						</view>

					</view>
				</view>

			</view>

		</gui-page>
	</view>
</template>

<script>
	var parserHtml = require("@/GraceUI5/js/parserHTML.js");

	export default {
		data() {
			return {
				time: new Date(),
				title: "",
				article: []
			}
		},
		onLoad: function(option) {
			console.log(option.id)
			uni.showLoading({
				title: "loading",
				mask: true
			});
			uni.setNavigationBarTitle({
				title: '加载中'
			});

			uni.request({
				url: "http://192.168.3.4:8081/bohe/getWenZhangById",
				method: "POST",
				data: {
					id: option.id
				},
				success: (res) => {
					console.log(res);
					this.time = res.data.data.createTime
					this.article = parserHtml.parserHTML(res.data.data.content);

					// this.article = res.data.data.content;
					// 文章标题 来自 api 加载
					this.title = res.data.data.title;
					uni.setNavigationBarTitle({
						title: this.title
					});
					uni.hideLoading();
				}
			})
		},
		methods: {

		}
	}
</script>

<style>

</style>