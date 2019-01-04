import React, { Component } from 'react'
import PropTypes from 'prop-types'
import { Form, Modal, FormGroup, FormControl, Button } from 'react-bootstrap';
import DatePicker from 'react-16-bootstrap-date-picker';
import TimeField from 'react-simple-timefield';

const INITIAL_DATE = new Date();
export default class EventForm extends Component {

    getInitialState() {
        const date = INITIAL_DATE.toISOString();
        return {
            name: '',
            minDate: date,
            date: '',
            formattedDate: '',
            time: '00:00',
            venueName: '',
            venueCity: '',
            venueState: '',
            sumbmitted: false,
        }
    }

    constructor(props) {
        super(props);
        this.state = this.getInitialState();
        this.handleChange = this.handleChange.bind(this);
        this.handleClose = this.handleClose.bind(this);
        this.handleDateChange = this.handleDateChange.bind(this);
        this.handleTimeChange = this.handleTimeChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.validateForm = this.validateForm.bind(this)
    }

    handleChange(e) {
        this.setState({ [e.target.name]: e.target.value });
    }

    handleDateChange(value, formattedDate) {
        this.setState({
            date: value,
            formattedDate
        });
    }

    handleClose() {
        this.setState(this.getInitialState());
        this.props.handleClose();
    }

    handleSubmit() {
        this.setState({ sumbmitted: true });
        if (this.validateForm()) {
            const { name, time, venueName, venueCity, venueState } = this.state;
            const formObject = { name, time, venueName, venueCity, venueState };
            formObject.date = this.state.formattedDate;
            this.props.handleSubmit(formObject);
            this.setState(this.getInitialState());
            this.props.handleClose();
        }
    }

    validateForm() {
        const { name, date, time, venueName, venueCity, venueState } = this.state;
        if (name.length === 0) return false;
        if (date.length === 0) return false;
        if (time.length === 0) return false;
        if (venueName.length === 0) return false;
        if (venueCity.length === 0) return false;
        if (venueState.length === 0) return false;
        return true;
    }

    handleTimeChange(value) {
        this.setState({
            time: value
        });
    }

    getValidationState(id) {
        if (this.state.sumbmitted) {
            const length = this.state[id].length;
            if (length > 0) return 'success';
            else if (length === 0) return 'error';
        }
        return null;
    }

    render() {
        const { show } = this.props;
        return (
            <Modal show={show} onHide={this.handleClose} bsSize="large">
                <Modal.Header closeButton>
                    <Modal.Title>Add New Event</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <div className="container col-md-12">
                        <Form>
                            <div className="row">
                                <h3 className="mt-0">Event Information</h3>
                                <div className="col-sm-4">
                                    <FormGroup
                                        controlId="name"
                                        validationState={this.getValidationState('name')}
                                    >
                                        <FormControl
                                            type="text"
                                            value={this.state.name}
                                            placeholder="Event Name"
                                            name="name"
                                            onChange={this.handleChange}
                                        />
                                        <FormControl.Feedback />
                                    </FormGroup>
                                </div>
                                <div className="col-sm-4">
                                    <FormGroup
                                        controlId="date"
                                        validationState={this.getValidationState('date')}
                                    >
                                        <DatePicker id="date" dateFormat="YYYY-MM-DD" minDate={this.state.minDate} value={this.state.date} onChange={this.handleDateChange} />
                                        <FormControl.Feedback />
                                    </FormGroup>
                                </div>
                                <div className="col-sm-4">
                                    <FormGroup
                                        controlId="time"
                                        validationState={this.getValidationState('time')}
                                    >
                                        <TimeField value={this.state.time} onChange={this.handleTimeChange} input={<FormControl type="text" name="time" />} />
                                        <FormControl.Feedback />
                                    </FormGroup>
                                </div>
                            </div>
                            <div className="row">
                                <h3 className="mt-0">Venue Information</h3>
                                <div className="col-sm-4">
                                    <FormGroup
                                        controlId="venueName"
                                        validationState={this.getValidationState('venueName')}
                                    >
                                        <FormControl
                                            type="text"
                                            value={this.state.venueName}
                                            placeholder="Venue Name"
                                            name="venueName"
                                            onChange={this.handleChange}
                                        />
                                        <FormControl.Feedback />
                                    </FormGroup>
                                </div>
                                <div className="col-sm-4">
                                    <FormGroup
                                        controlId="venueCity"
                                        validationState={this.getValidationState('venueCity')}
                                    >
                                        <FormControl
                                            type="text"
                                            value={this.state.venueCity}
                                            placeholder="Venue City"
                                            name="venueCity"
                                            onChange={this.handleChange}
                                        />
                                        <FormControl.Feedback />
                                    </FormGroup>
                                </div>
                                <div className="col-sm-4">
                                    <FormGroup
                                        controlId="time"
                                        validationState={this.getValidationState('venueState')}
                                    >
                                        <FormControl componentClass="select" name="venueState" placeholder="Venue State" onChange={this.handleChange}>
                                            <option value=""></option>
                                            <option value="AK">AK</option>
                                            <option value="AL">AL</option>
                                            <option value="AR">AR</option>
                                            <option value="AZ">AZ</option>
                                            <option value="CA">CA</option>
                                            <option value="CO">CO</option>
                                            <option value="CT">CT</option>
                                            <option value="DC">DC</option>
                                            <option value="DE">DE</option>
                                            <option value="FL">FL</option>
                                            <option value="GA">GA</option>
                                            <option value="HI">HI</option>
                                            <option value="IA">IA</option>
                                            <option value="ID">ID</option>
                                            <option value="IL">IL</option>
                                            <option value="IN">IN</option>
                                            <option value="KS">KS</option>
                                            <option value="KY">KY</option>
                                            <option value="LA">LA</option>
                                            <option value="MA">MA</option>
                                            <option value="MD">MD</option>
                                            <option value="ME">ME</option>
                                            <option value="MI">MI</option>
                                            <option value="MN">MN</option>
                                            <option value="MO">MO</option>
                                            <option value="MS">MS</option>
                                            <option value="MT">MT</option>
                                            <option value="NC">NC</option>
                                            <option value="ND">ND</option>
                                            <option value="NE">NE</option>
                                            <option value="NH">NH</option>
                                            <option value="NJ">NJ</option>
                                            <option value="NM">NM</option>
                                            <option value="NV">NV</option>
                                            <option value="NY">NY</option>
                                            <option value="OH">OH</option>
                                            <option value="OK">OK</option>
                                            <option value="OR">OR</option>
                                            <option value="PA">PA</option>
                                            <option value="RI">RI</option>
                                            <option value="SC">SC</option>
                                            <option value="SD">SD</option>
                                            <option value="TN">TN</option>
                                            <option value="TX">TX</option>
                                            <option value="UT">UT</option>
                                            <option value="VA">VA</option>
                                            <option value="VT">VT</option>
                                            <option value="WA">WA</option>
                                            <option value="WI">WI</option>
                                            <option value="WV">WV</option>
                                            <option value="WY">WY</option>
                                        </FormControl>
                                        <FormControl.Feedback />
                                    </FormGroup>
                                </div>
                            </div>
                        </Form>
                    </div>
                </Modal.Body>
                <Modal.Footer>
                    <Button onClick={this.handleClose}>Close</Button>
                    <Button onClick={this.handleSubmit}>Add Event</Button>
                </Modal.Footer>
            </Modal>
        )
    }
}

EventForm.propTypes = {
    show: PropTypes.bool.isRequired,
    handleClose: PropTypes.func.isRequired,
    handleSubmit: PropTypes.func.isRequired,
}