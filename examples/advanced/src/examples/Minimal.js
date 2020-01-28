import React, { Component } from 'react'
import DatePicker from 'react-native-date-picker'

export default class MinimalExample extends Component {
  state = { date: new Date() }

  render = () => (
    <DatePicker
      date={this.state.date}
      mode="time"
      onDateChange={date => this.setState({ date })}
      locale={'en_GB'}
    />
  )
}
