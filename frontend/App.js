/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React from 'react';
import {View, StyleSheet, useColorScheme} from 'react-native';
import Navbar from './components/navbar';
import InnerScreen from './components/innerscreen';

const App = () => {
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

export default App;
