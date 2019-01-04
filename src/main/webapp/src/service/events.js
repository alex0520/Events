import axios from 'axios';

const API_URL = 'http://localhost:8080/api';
const EVENTS_URL = API_URL + '/events'
const CREATE_EVENTS_URL = EVENTS_URL + '/venue'

export function getEvents() {
    return axios.get(EVENTS_URL);
}

export function createEvents(eventName, eventDate, venueName, venueCity, venueState) {
    const data = {
        "name": eventName,
        "date": eventDate,
        "venue": {
            "name": venueName,
            "city": venueCity,
            "state": venueState
        }
    }
    return axios.post(CREATE_EVENTS_URL, data);
}