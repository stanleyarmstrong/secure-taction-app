/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React from 'react';
import {View, useColorScheme} from 'react-native';
import Navbar from './components/navbar';

const App = () => {
  return (
    <View>
      <Navbar
        title="Good Afternoon, First Last"
        backIcon="account-circle-outline"
        frontIcon="bell-outline"
      />
    </View>
  );
};

export default App;
