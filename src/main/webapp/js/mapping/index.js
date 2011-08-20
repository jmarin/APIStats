$(document).ready(
		function() {
			OpenLayers.ImgPath = "http://www.nbmtest.net/nbm/img/openlayers/dark/";
			var mapOptions = { 
					resolutions: [156543.03390625, 78271.516953125, 39135.7584765625, 19567.87923828125, 9783.939619140625, 4891.9698095703125, 2445.9849047851562, 1222.9924523925781, 611.4962261962891, 305.74811309814453, 152.87405654907226, 76.43702827453613, 38.218514137268066, 19.109257068634033, 9.554628534317017, 4.777314267158508, 2.388657133579254, 1.194328566789627, 0.5971642833948135, 0.29858214169740677, 0.14929107084870338, 0.07464553542435169, 0.037322767712175846, 0.018661383856087923, 0.009330691928043961, 0.004665345964021981],
					projection: new OpenLayers.Projection('EPSG:900913'),
					maxExtent: new OpenLayers.Bounds(-2.003750834E7,-2.003750834E7,2.003750834E7,2.003750834E7),
					units: "meters",
					controls: []
			};
			
			//Cloudmade base map
			var cloudmade = new OpenLayers.Layer.CloudMade("CloudMade", {
				key: '76e5147f19c4497d94751708a16d4590',
				styleId: 43327
			});
		 
			map = new OpenLayers.Map('mainMap', mapOptions);
			map.addControl(new OpenLayers.Control.PanZoomBar({
				position: new OpenLayers.Pixel(2, 15)
			}));
					
			map.addControl(new OpenLayers.Control.Navigation());
				
			map.addLayers([cloudmade]);
		 
			var initialExtent = new OpenLayers.Bounds(-125,24,-70,47).transform(new OpenLayers.Projection("EPSG:4326"),new OpenLayers.Projection(map.getProjection()));
			map.zoomToExtent(initialExtent);
		});