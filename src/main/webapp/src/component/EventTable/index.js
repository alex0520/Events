import React, { Component } from 'react';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import BootstrapTable from 'react-bootstrap-table-next';
import PropTypes from 'prop-types'

const columns = [
{
  dataField: 'name',
  text: 'Event Name'
},
{
  dataField: 'date',
  text: 'Date'
},
{
  dataField: 'venue.name',
  text: 'Venue'
},
{
    dataField: 'venue.city',
    text: 'City'
},
{
    dataField: 'venue.state',
    text: 'State'
}];

export default class EventTable extends Component {
  render() {
    const {events} = this.props;
    return (
        <BootstrapTable keyField='id' data={ events } columns={ columns } />
    )
  }
}

EventTable.propTypes = {
    events: PropTypes.array.isRequired,
};


