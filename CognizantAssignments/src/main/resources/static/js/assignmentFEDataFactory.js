angular
.module('app').factory("assignmentFEDataFactory",function(){
	var savedData = [];
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