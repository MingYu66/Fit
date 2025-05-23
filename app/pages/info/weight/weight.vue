<template>
	<view>
		<view style="margin-top: 0;" class="card-body gui-card-body  gui-border-b card">
			<view style="color: #2db7f5;margin-bottom: 10rpx;display: flex; align-items: center;"
				>
				<view><text class="gui-grids-icon gui-icons" style="font-size: 20px;">
					</text></view>
				<view style="margin-left: 10px;">
					当前身体状况
				</view>
			</view>
		
			<view class="nav " style="display: flex;align-items:center; justify-content: center;">
				<view class="yuan_text">
					当前体重
					<view class="gui-bold">
						{{kg}}
					</view>
				</view>
				<view>
					<view class="" style="text-align: center;">
						健康状况
					</view>
					<view class="" style="margin-top: 10rpx;">
						{{kg > 0 && dk ? '今日体重：' + kg + 'KG' : '未打卡'}}
					</view>
				</view>
		
				<view class="yuan_text">
					BMI指数
					<view class="gui-bold">
						{{bmi.toFixed(2)}}
					</view>
				</view>
			</view>
		</view>
		
		<view class="charts-box">
			<qiun-data-charts type="line" :opts="opts" :chartData="chartData" />
		</view>
		
	</view>
</template>

<script>
		var graceJS = require('@/GraceUI5/js/grace.js');
	export default {
		data() {
			return {
				bmi: 0,
				date1: null,
				kg:0,
				dk:false,
				chartData: {},
				//您可以通过修改 config-ucharts.js 文件中下标为 ['line'] 的节点来配置全局默认参数，如都是默认参数，此处可以不传 opts 。实际应用过程中 opts 只需传入与全局默认参数中不一致的【某一个属性】即可实现同类型的图表显示不同的样式，达到页面简洁的需求。
				opts: {
					color: ["#1890FF", "#91CB74", "#FAC858", "#EE6666", "#73C0DE", "#3CA272", "#FC8452", "#9A60B4",
						"#ea7ccc"
					],
					padding: [15, 10, 0, 15],
					dataLabel: false,
					dataPointShape: false,
					enableScroll: false,
					legend: {},
					xAxis: {
						disableGrid: true,
						fontSize: 10,
						rotateLabel: true
					},
					yAxis: {
						gridType: "dash",
						dashLength: 2,
						data: [{
							min: 0,
							max: 150
						}]
					},
					extra: {
						line: {
							type: "curve",
							width: 2,
							activeType: "hollow",
							linearType: "custom",
							onShadow: true,
							animation: "horizontal"
						},
					}
				}
			}
		},
		onLoad() {
			var date = new Date();
		
			this.date1 = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
		
			this.getWeight()
			
		},
		methods: {
			getWeight() {
				uni.gRequest.post("bohe/getDayWeight", {
					data: {
						userId: graceJS.getStorage("id")
					}
				}, (res) => {
					console.log(res.data.data)
					if (res.data.data.length > 0) {
						this.kg = res.data.data[0].weight
						this.dk = true
					} else {
						uni.gRequest.post("bohe/getUserWeight", {
							data: {
								userId: graceJS.getStorage("id"),
							}
						}, (resList) => {
							console.log(resList)
							this.kg = resList.data.data[0].weight
			
						})
						this.dk = false
					}
					this.calculateBMI()
					this.getServerData()
				})
				
			},
			//BMI计算
			calculateBMI() {
				// this.weight = graceJS.getStorage("weight")
				this.height = graceJS.getStorage("height") / 100
				console.log(this.kg, this.height)
				if (this.kg && this.height) {
					const heightSquared = this.height * this.height;
					this.bmi = this.kg / heightSquared;
				} else {
					alert('请输入有效的体重和身高');
				}
			},
			getServerData() {
				uni.gRequest.post("bohe/getUserWeight", {
					data: {
						userId: graceJS.getStorage("id"),
						// weight: this.kg
					}
				}, (res) => {
					console.log(res.data.data)
					var days = []
					var weight = []
					var bmi = []
					const heightSquared = this.height * this.height;
					
					this.weight = res.data.data[0].weight
					for (let s of res.data.data) {
						if (days.length < 15) {
							days.push(s.createTime)
							weight.push(s.weight)
							bmi.push((s.weight / heightSquared).toFixed(2))
						}
					}
					var ast = {
						categories: days,
						xAxis: {
							fontSize: 10
						},
						series: [{
								// name: "血糖记录",
								name: "体重记录",
								// 
								linearColor: [
									[
										0,
										"#1890FF"
									],
									[
										0.25,
										"#00B5FF"
									],
									[
										0.5,
										"#00D1ED"
									],
									[
										0.75,
										"#00E6BB"
									],
									[
										1,
										"#90F489"
									]
								],
								setShadow: [
									3,
									8,
									10,
									"#1890FF"
								],
								data: weight
							},
							{
									name: "BMI",
									// 
									linearColor: [
										[
											0,
											"#e4ff30"
										],
										[
											0.25,
											"#6effa8"
										],
										[
											0.5,
											"#e137ed"
										],
										[
											0.75,
											"#e6b4dd"
										],
										[
											1,
											"#90F489"
										]
									],
									setShadow: [
										3,
										8,
										10,
										"#f7ff57"
									],
									data: bmi
								},
			
						]
					}
			
					this.chartData = JSON.parse(JSON.stringify(ast))
			
			
				})
			},
		}
	}
</script>

<style>

	.card-body {
		margin-top: 50upx;
		padding: 20upx;
		background-color: white;
		/* border: 1upx solid red; */
	}
.nav {
		margin: 20upx 0 20upx 0;
	}
.yuan_text {
		width: 30%;
		text-align: center;
	}
</style>
