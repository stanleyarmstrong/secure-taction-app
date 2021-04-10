import React from 'react';
import {View, StyleSheet, useColorScheme} from 'react-native';
import Navbar from './components/navbar';
import InnerScreen from './components/innerscreen';

const HomeScreen = (props) => {
  return (
    <View style={styles.shell}>
      <Navbar
        title="Good Afternoon, First Last"
        backIcon="account-circle-outline"
        frontIcon="bell-outline"
      />
      <InnerScreen title="Your Cards" />
    </View>
  );
};

const styles = StyleSheet.create({
  shell: {
    backgroundColor: '#E9E9E9',
    height: '100%',
  },
});
export default HomeScreen;
