import React, { Component } from 'react';
import EventTable from '../component/EventTable';
import { getEvents, createEvents } from '../service/events'
import EventForm from '../component/EventForm';
import { Button } from 'react-bootstrap';

export default class Main extends Component {

    constructor() {
        super();
        this.state = {
            events: [],
            showAddModal: false,
        };
    }

    handleCloseAddModal = () => {
        this.setState({ showAddModal: false });
    }

    handleShowAddModal = () => {
        this.setState({ showAddModal: true });
    }

    handleSubmitAddModal = (formObject) => {
        const _this = this;
        createEvents(formObject.name,`${formObject.date}T${formObject.time}`,formObject.venueName, formObject.venueCity, formObject.venueState)
        .then(function(response){
            _this.setState(prevState => ({
                events: [...prevState.events, response.data],
              }))
        });
    }

    componentDidMount() {
        const _this = this;
        getEvents().then(function (response) {
            _this.setState({ events: response.data });
        });
        //createEvents("TestService","2019-01-04T09:19:21","Venue Name", "City", "IL");
    }
    render() {
        const { events, showAddModal } = this.state;
        return (
            <div className="Main">
                <EventTable events={events} />
                <Button onClick={this.handleShowAddModal}>Add Event</Button>
                <EventForm show={showAddModal} handleClose={this.handleCloseAddModal} handleSubmit={this.handleSubmitAddModal} />
            </div>
        )
    }
}
