import React from 'react';
import {View, TouchableOpacity, StyleSheet} from 'react-native';
import {Text, IconButton, DataTable} from 'react-native-paper';
import {useNavigation} from '@react-navigation/native';

export const AddBudget = () => {
  const navigation = useNavigation();
  return (
    <View style={styles.addBudget}>
      <TouchableOpacity
        onPress={() => {
          navigation.push('addbudget');
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

export const Budget = (props) => {
  return (
    <DataTable>
      <DataTable.Header>
        <DataTable.Title> Budget Info: </DataTable.Title>
        <DataTable.Title numeric> Value: </DataTable.Title>
      </DataTable.Header>
      <DataTable.Row>
        <DataTable.Cell> Current Spending: </DataTable.Cell>
        <DataTable.Cell numeric>
          ${props.currentBudget.toFixed(2)}
        </DataTable.Cell>
      </DataTable.Row>
      <DataTable.Row>
        <DataTable.Cell> Maximum Spending: </DataTable.Cell>
        <DataTable.Cell numeric> ${props.maxBudget.toFixed(2)} </DataTable.Cell>
      </DataTable.Row>
      <DataTable.Row>
        <DataTable.Cell> Minimum Alert: </DataTable.Cell>
        <DataTable.Cell numeric> ${props.alert.toFixed(2)} </DataTable.Cell>
      </DataTable.Row>
      <DataTable.Row>
        <DataTable.Cell> Auto-Cancel: </DataTable.Cell>
        <DataTable.Cell numeric> ${props.cancel.toFixed(2)} </DataTable.Cell>
      </DataTable.Row>
    </DataTable>
  );
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
