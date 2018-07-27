# Die bunte Schweiz - Daten auf einer Karte Visualisieren
## d3.js
* http://bl.ocks.org/mbostock

### TopoJSON
* https://en.wikipedia.org/wiki/GeoJSON#TopoJSON_Schema
* https://github.com/mbostock/topojson/wiki
* http://bost.ocks.org/mike/map/

### The shape

* https://github.com/interactivethings/swiss-maps
```
mapshaper combine-files encoding=utf8 *.shp -o map.json format=topojson
mapshaper map.json encoding=utf8 -info
mapshaper map.json -dissolve PLZ target=PLZO_PLZ -simplify visvalingam 2% -o format=topojson map.simple.json
```
Steal one:

* https://github.com/interactivethings/swiss-maps#examples 

```
wget http://bl.ocks.org/mbostock/raw/4207744/ 
wget http://bl.ocks.org/mbostock/raw/4207744/readme-swiss.json 
```

### Data

DurschSchnittsalter.sql -> alter.json

### All together

canton style löschen

```javascript
function color(v){
	var off = 48;
	var step = 0.5;
	var i = 0;
	if(v <= off + (i++) * step) return "#ffffff";
	if(v <= off + (i++) * step) return "#4db300";
	if(v <= off + (i++) * step) return "#65bf00";
	if(v <= off + (i++) * step) return "#7dcc00";
	if(v <= off + (i++) * step) return "#9bd900";
	if(v <= off + (i++) * step) return "#bbe600";
	if(v <= off + (i++) * step) return "#def200";
	if(v <= off + (i++) * step) return "#ffff00";
	if(v <= off + (i++) * step) return "#ffdd00";
	if(v <= off + (i++) * step) return "#ffb700";
	if(v <= off + (i++) * step) return "#ff9100";
	if(v <= off + (i++) * step) return "#ff6f00";
	if(v <= off + (i++) * step) return "#ff4800";
	return "#ff2600";
}

d3.json("alter.json", function(error, alter) {
	d3.json("readme-swiss.json", function(error, swiss) {
	...
	cantons.features.forEach(function(canton) {
	...
		.attr("fill", color(alter[canton.id]))
	...
	.text(function(d) {return d.properties.name +" "+ parseInt(alter[d.id]*10)/10;});
	...
}
```

Die faule Variante: http://datamaps.github.io/

## map
* https://test-target.balgroupit.com/rad/map/?lang=en&labels=hidden

### more features
* http://eligrey.com/demos/FileSaver.js/
* https://github.com/sterlingwes/RandomColor
* http://html5demos.com/file-api
* replaceState()
* CORS

