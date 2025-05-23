<template>
	<view class="l-circle" :class="[classes]" :style="[styles]">
		<!-- #ifndef APP-NVUE -->
		<view class="l-circle__trail" :style="[trailStyles]">
			<text class="cap start"></text>
			<text class="cap end"></text>
		</view>
		<view class="l-circle__stroke" :style="[strokeStyles]">
			<text class="cap start" v-if="current"></text>
			<text class="cap end" v-if="current"></text>
		</view>
		<!-- #endif -->
		<!-- #ifdef APP-NVUE -->
		<web-view @pagefinish="finished = true" class="l-circle__view" ref="webview"
			src="/uni_modules/l-circle/hybrid/html/index.html"></web-view>
		<!-- #endif -->
		<view class="l-circle__inner">
			<slot></slot>
		</view>
	</view>
</template>

<script lang="ts">
	// @ts-nocheck
	import { ref, computed, onMounted, watch, reactive, defineComponent } from './vue';
	import { toPx, addUnit } from './utils';
	import { useTransition } from './useTransition';
	import CircleProps from './props';
	// import { toPx, addUnit } from '@/uni_modules/lime-ui';
	// import { useTransition } from '@/uni_modules/lime-use';
	
	

	export default defineComponent({
		name: 'l-circle',
		props: CircleProps,
		emits: ['update:current'],
		setup(props, {emit}) {
			const RADIAN = Math.PI / 180
			const ratio = computed(() => 100 / props.max)
			const percent = ref<number>(0)
			const angle = computed(() => props.dashborad ? 135 : -90)
			const isShowCap = computed(() => {
				const { dashborad } = props
				return current.value > 0 && (dashborad ? true : current.value < props.max)
			})

			const offsetTop = ref<number | string>(0)
			const strokeEndCap = reactive({
				x: '0',
				y: '0'
			})

			const styles = computed(() => ({
				width: props.size,
				height: props.size,
				// #ifdef APP-NVUE
				transform: `translateY(${offsetTop.value})`,
				// #endif
				// #ifndef APP-NVUE
				'--l-circle-offset-top': offsetTop.value,
				// #endif
			}))
			const classes = computed(() => {
				const { clockwise, lineCap } = props
				return {
					clockwise,
					[`is-${lineCap}`]: lineCap
				}
			})
			const trailStyles = computed(() => {
				const { size, trailWidth, trailColor, dashborad } = props
				const circle = getCircle(size, trailWidth)
				const mask = `radial-gradient(transparent ${circle.r - 0.5}px, #000 ${circle.r}px)`

				let background = ''
				let capStart = { x: '', y: '' }
				let capEnd = capStart

				if (dashborad) {
					background = `conic-gradient(from 225deg, ${trailColor} 0%, ${trailColor} 75%, transparent 75%, transparent 100%)`
					capStart = calcPosition(circle.c, 135)
					capEnd = calcPosition(circle.c, 45)
					offsetTop.value = (toPx(size) - (toPx(capStart.y) + toPx(trailWidth) / 2)) / 4 + 'px'
				} else {
					background = `${trailColor}`
				}

				return {
					color: trailColor,
					mask,
					'-webkit-mask': mask,
					background,
					'--l-circle-trail-cap-start-x': capStart.x,
					'--l-circle-trail-cap-start-y': capStart.y,
					'--l-circle-trail-cap-end-x': capEnd.x,
					'--l-circle-trail-cap-end-y': capEnd.y,
					// '--l-circle-trail-cap-color': trailColor,
					'--l-circle-trail-cap-size': addUnit(trailWidth)
				}
			})
			const strokeStyles = computed(() => {
				const { size, strokeWidth, strokeColor, duration, dashborad, max } = props
				const circle = getCircle(size, strokeWidth)
				const percent = dashborad ? current.value * 0.75 * ratio.value : current.value * ratio.value;
				const mask = `radial-gradient(transparent ${circle.r - 0.5}px, #000 ${circle.r}px)`
				const cap = calcPosition(circle.c, angle.value)

				let startColor = '';
				let endColor = '';

				let gradient = `conic-gradient(${dashborad ? 'from 225deg,' : ''} transparent 0%,`;
				let gradientEnd = `transparent var(--l-circle-percent), transparent ${dashborad ? '75%' : '100%'})`

				if (typeof strokeColor == 'string') {
					gradient += ` ${strokeColor} 0%, ${strokeColor} var(--l-circle-percent), ${gradientEnd}`
					startColor = endColor = strokeColor
				} else {
					const len = strokeColor.length
					for (let i = 0; i < len; i++) {
						const color = strokeColor[i] as string
						if (i === 0) {
							gradient += `${color} 0%,`
							startColor = color
						} else {
							gradient += `${color} calc(var(--l-circle-percent) * ${(i + 1) / len}),`
						}
						if (i == len - 1) {
							endColor = color
						}
					}
					gradient += gradientEnd
				}
				const a = percent / ratio.value == max ? percent + 0.1 : percent
				return {
					mask,
					'-webkit-mask': mask,
					background: gradient,
					// transition: `--l-circle-percent ${duration}ms`,
					'--l-circle-percent': `${percent / ratio.value == max ? percent + 0.1 : percent}%`,
					'--l-circle-stroke-cap-start-color': startColor,
					'--l-circle-stroke-cap-end-color': endColor,
					'--l-circle-stroke-cap-start-x': cap.x,
					'--l-circle-stroke-cap-start-y': cap.y,
					'--l-circle-stroke-cap-end-x': strokeEndCap.x,
					'--l-circle-stroke-cap-end-y': strokeEndCap.y,
					'--l-circle-stroke-cap-size': addUnit(strokeWidth),
					'--l-circle-stroke-cap-opacity': isShowCap.value ? 1 : 0
				}
			})
			const calcStrokeCap = () => {
				const { size, strokeWidth, dashborad, max } = props
				const circle = getCircle(size, strokeWidth)
				const arc = dashborad ? 180 / 2 * 3 : 180 * 2
				const step = arc / max * current.value + angle.value
				const cap = calcPosition(circle.c, step)

				strokeEndCap.x = cap.x
				strokeEndCap.y = cap.y
			}

			const calcPosition = (r : number, angle : number) => {
				return {
					x: r + r * Math.cos(angle * RADIAN) + 'px',
					y: r + r * Math.sin(angle * RADIAN) + 'px'
				}
			}

			const getCircle = (size : number | string, lineWidth : number | string) => {
				const s = toPx(size)
				const w = toPx(lineWidth)
				const c = (s - w) / 2
				const r = s / 2 - w
				return {
					s, w, c, r
				}
			}
			
			const current = useTransition(percent, {
				duration: props.duration,
			})
			watch(() => props.percent, () => {
				percent.value = props.percent

			}, { immediate: true })
			watch(current, (v) => {
				calcStrokeCap()
				emit('update:current', v.toFixed(2))
			})

			// #ifdef APP-NVUE
			const finished = ref(false)
			const webview = ref(null)
			watch(finished, () => {
				webview.value.evalJs(`setClass('.l-circle', 'is-round', ${props.lineCap == 'round'})`)
				webview.value.evalJs(`setClass('.l-circle', 'clockwise', ${props.clockwise})`)

				watch([trailStyles, strokeStyles], (v) => {
					webview.value.evalJs(`setStyle(0,${JSON.stringify(v[0])})`)
					webview.value.evalJs(`setStyle(1,${JSON.stringify(v[1])})`)
				}, { immediate: true })
			})
			// #endif
			
			return {
				classes,
				styles,
				trailStyles,
				strokeStyles,
				current,
				// #ifdef APP-NVUE
				finished
				// #endif
			}
		}
	})
</script>
<style lang="scss">
	@import './index';
</style>