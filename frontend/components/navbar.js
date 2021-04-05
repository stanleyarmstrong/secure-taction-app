import React from 'react';
import {StyleSheet} from 'react-native';
import {Appbar} from 'react-native-paper';
import {View} from 'react-native';

const Navbar = (props) => {
  return (
    <View>
      <Appbar.Header style={styles.barColor}>
        <Appbar.Action icon={props.backIcon} size={35} />
        <Appbar.Content title={props.title} titleStyle={styles.title} />
        <Appbar.Action icon={props.frontIcon} size={35} />
      </Appbar.Header>
    </View>
  );
};

const styles = StyleSheet.create({
  barColor: {
    backgroundColor: '#00A7E1',
  },
  title: {
    fontSize: 18,
  },
});

export default Navbar;
