import {
    Platform,
    NativeModules,
    NativeAppEventEmitter,
    DatePickerIOS,
    Text,
    requireNativeComponent,
    ViewPropTypes
} from 'react-native';
import React, { Component } from 'react'
import PropTypes from 'prop-types';

const ios = Platform.OS === 'ios';

const NativeDatePicker = requireNativeComponent(`DatePickerManager`, DatePickerAndroid, { nativeOnly: { onChange: true } });

class DatePickerAndroid extends React.Component {

    _onChange = e => this.props.onDateChange(new Date(parseInt(e.nativeEvent.date)));
    render = () => <NativeDatePicker {...this.props} date={this.props.date.getTime()} onChange={this._onChange} />;
}

DatePickerAndroid.propTypes = DatePickerIOS.propTypes;

export default ios ? DatePickerIOS : DatePickerAndroid;