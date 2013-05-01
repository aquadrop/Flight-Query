$(document).ready(function(){
	$('#deptCity').typeahead({
		source: airports,
		//source: queryAirports,
		updater: function(item) {
			var matches = item.match(/[A-Z]{3}/);
			$('input[name=deptCity]').val(matches[0]);
			return item;
		}
	}).focus(function(){
		$(this).select()
	});
	
	$('#destCity').typeahead({
		source: airports,
		//source: queryAirports,
		updater: function(item) {
			var matches = item.match(/[A-Z]{3}/);
			$('input[name=destCity]').val(matches[0]);
			return item;
		}
	}).focus(function(){
		$(this).select()
	});
	
	$('#searchFlightForm').submit(queryRoutes);

	$('#myModal').on('hidden', function() {
		$(this).removeData('modal');
	});
});

function queryAirports(q, p) {
	var airports = [];
	$.get('SearchFlight', {
		'type' : 'airport',
		'q' : q
	}, function(data) {
		for (var i in data.airports) {
			airports.push(data.airports[i]);
		}

		p(airports);
	});
}

function queryRoutes() {
	$.get('SearchFlight', {
		'type' : 'route',
		'dept' : $('input[name=deptCity]').val(),
		'dest' : $('input[name=destCity]').val()
	}, function(data) {
		$table = $('<table class="table table-striped table-bordered" />');
		$thead = $('<thead><tr>' +
			'<th>Airline</th>' +
			'<th>Source Airport</th>' +
			'<th>Destination Airport</th>' +
			'<th>Equipment</th>' +
			'</tr></thead>');
		
		$tbody = $('<tbody />');
		for (var i in data.routes) {
			$airline = $('<a />').attr('href', 'Details?type=airline&iata=' + data.routes[i].Airline).attr('data-toggle', 'modal').attr('data-target', '#myModal').html(data.routes[i].Airline);
			$deptAirport = $('<a />').attr('href', 'Details?type=airport&iata=' + data.routes[i].DeptAirport).attr('data-toggle', 'modal').attr('data-target', '#myModal').html(data.routes[i].DeptAirport);
			$destAirport = $('<a />').attr('href', 'Details?type=airport&iata=' + data.routes[i].DestAirport).attr('data-toggle', 'modal').attr('data-target', '#myModal').html(data.routes[i].DestAirport);

			$row = $('<tr />');
			$row.append($('<td />').append($airline));
			$row.append($('<td />').append($deptAirport));
			$row.append($('<td />').append($destAirport));
			$row.append($('<td />').append(data.routes[i].Equipment));
			
			$tbody.append($row);
		}
		
		$table.append($thead).append($tbody);
		
		$('#searchResult').html($table);
	});
	
	return false;
}