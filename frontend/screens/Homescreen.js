import React from 'react';
import {View, StyleSheet, useColorScheme} from 'react-native';
import InnerScreen from '../components/innerscreen';

const HomeScreen = (props) => {
  return (
    <View style={styles.shell}>
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
