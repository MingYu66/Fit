<template>
	<view>
		<demo-block title="基础">
			<l-circle v-model:current="modelValue1" :percent="target1" size="200rpx">
				<text>{{modelValue1}}%</text>
			</l-circle>
			
			<l-circle v-model:current="modelValue1" :percent="target1" :dashborad="false"  :clockwise="false" :stroke-color="['#0000ff', '#ff0000', '#ffa500']" size="200rpx">
				<text>{{modelValue1}}%</text>
			</l-circle>
			<l-circle v-model:current="modelValue1" :percent="target1" :dashborad="true"  :clockwise="true" :stroke-color="['#0000ff', '#ff0000']" size="200rpx">
				<text>{{modelValue1}}%</text>
			</l-circle>
			<button @tap="onClick(20)">+</button>
			<button @tap="onClick(-20)">-</button>
		</demo-block>
		<demo-block>
			<l-circle v-model:current="modelValue2" :percent="target4" :max="60" :dashborad="false" :clockwise="true" >
				<text>{{modelValue2}}</text>
			</l-circle>
			<button @tap="onPlay">播放</button>
			<button @tap="onStop">暂停</button>
		</demo-block>
	</view>
</template>
<script>
	import {ref, defineComponent} from '../l-circle/vue';
	export default defineComponent({
		setup() {
			const target1 = ref(50)
			const modelValue1 = ref(0)
			
			const target4 = ref(60)
			const modelValue2 = ref(0)
			
			const onClick = number => {
				target1.value = Math.max(Math.min(100, target1.value + number), 0)
			}
			
			let timer = null
			let isPlay = false
			const countDown  = () => {
				target4.value -= 1
				if(target4.value) {
					timer = setTimeout(countDown,1000)
				}
			}
			const onStop = () => {
				isPlay = false
				clearTimeout(timer)
			}
			const onPlay = () => {
				if(isPlay) return
				isPlay = true
				timer = setTimeout(countDown,1000)
			}
			
			const popup = ref(null)
			const onOpen = () => {
				if(popup.value) {
					popup.value.open()
				}
			}
			
			return {
				target1,
				target4,
				modelValue1,
				modelValue2,
				
				onClick,
				popup,
				onOpen,
				onStop,
				onPlay
			}
		}
	})
	
</script>
<style>
	
</style>
