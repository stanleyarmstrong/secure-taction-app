import React from 'react';
import {View, TouchableOpacity, StyleSheet} from 'react-native';
import {Text, IconButton} from 'react-native-paper';
import {useNavigation} from '@react-navigation/native';

export const AddBudget = () => {
  const navigation = useNavigation();
  return (
    <View style={styles.addBudget}>
      <TouchableOpacity
        onPress={() => {
          //will change to actual screen once I make that screen
          navigation.push('home');
        }}>
        <View style={styles.addInner}>
          <IconButton
            icon={'plus'}
            color={'#000000'}
            size={40}
            style={styles.addIcon}
          />
          <Text style={styles.addText}> Add Budget </Text>
        </View>
      </TouchableOpacity>
    </View>
  );
};

export const Budget = () => {
  return;
};

const styles = StyleSheet.create({
  addBudget: {
    marginTop: 25,
    marginBottom: 25,
    height: 100,
    borderWidth: 1,
    borderStyle: 'dashed',
    borderColor: '#C2C2C2',
  },
  addInner: {
    alignSelf: 'center',
  },
  addText: {
    color: '#007AE1',
    fontSize: 14,
  },
  addIcon: {},
});
