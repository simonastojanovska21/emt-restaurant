import React from "react";
import L from 'leaflet';
import { MapContainer, Marker, Popup, TileLayer } from "react-leaflet";
import 'leaflet/dist/leaflet.css';


let greenIcon = new L.Icon({
    iconUrl: 'https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-green.png',
    shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
    iconSize: [25, 41],
    iconAnchor: [12, 41],
    popupAnchor: [1, -34],
    shadowSize: [41, 41]
});

L.Marker.prototype.options.icon = greenIcon;

const LocationMap=()=> {

    return (
        <MapContainer center={[42.00450700813671, 21.4094887584795]} zoom={16}>
            <TileLayer
                attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />
            <Marker position={[42.00450700813671, 21.4094887584795]}>
                <Popup>
                    Руѓер Бошковиќ <br /> 1000 Скопје, Македонија
                </Popup>
            </Marker>
        </MapContainer>
    );
}

export default LocationMap;