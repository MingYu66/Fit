// @ts-nocheck
import {ease} from './animation/ease';
import {Timeline, Animation} from './animation/';
import {ref, watch, Ref} from './vue'
	
export function useTransition(percent: Ref<number>, options: {duration: number}) {
	const current = ref(0)
	let tl = new Timeline();
	
	watch(() => percent.value, (v) => {
		tl.start();
		tl.add(
		  new Animation(
		    current.value,
		    v,
		    options.duration,
		    0,
		    ease,
		    v => {
				current.value = v < 0.0001 ? 0: v
			}
		  )
		);
	}, {immediate: true})
	
	return current
}