import React from 'react';
import {StyleSheet} from 'react-native';
import {Appbar} from 'react-native-paper';
import {View} from 'react-native';

const Navbar = props => {
  return (
    <View>
      <Appbar.Header style={styles.barColor}>
        <Appbar.BackAction icon={props.backIcon} />
        <Appbar.Content title={props.title} />
        <Appbar.Action icon={props.frontIcon} />
      </Appbar.Header>
    </View>
  );
};

const styles = StyleSheet.create({
  barColor: {
    backgroundColor: '#00A7E1',
  }
});

export default Navbar;
