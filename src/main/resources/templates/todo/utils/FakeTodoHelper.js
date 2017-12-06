export function fakeTodoHelper(count) {
	let k = [];
	for (let i=0; i<count; i++) {
		k.push({
			name: "generated_" + i,
			desc: "desc",
			status: false
		});
	}
	return k;
}