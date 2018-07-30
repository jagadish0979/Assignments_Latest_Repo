angular
.module('app').factory("errorDataFactory",function(){
	var savedData = {};
	function set(data){
		savedData = data;
	}
	function get(data){
		return savedData;
	}
	return{
		set : set,
		get : get
	}
});
