import React, {useState, useEffect} from 'react';
import {View, TouchableOpacity, StyleSheet} from 'react-native';
import {Text, DataTable, Button} from 'react-native-paper';
import {useNavigation} from '@react-navigation/native';

export const RecentActivity = () => {
  const navigation = useNavigation();
  //replace with state once transactions endpoint/service is done
  const transactions = [
    {
      id: 0,
      from: "Ralph's Grocery",
      amount: -100.29,
    },
    {
      id: 1,
      from: 'Cisco Systems',
      amount: 1500.67,
    },
    {
      id: 2,
      from: 'Grocery Outlet',
      amount: -14.29,
    },
  ];
  const transactionComponents = transactions.map((transaction) => {
    return (
      <DataTable.Row key={transaction.id}>
        <DataTable.Cell numeric={false}> {transaction.from} </DataTable.Cell>
        <DataTable.Cell numeric={true}> ${transaction.amount} </DataTable.Cell>
      </DataTable.Row>
    );
  });
  return (
    <View style={styles.top}>
      <Text style={styles.text}> Recent Activity: </Text>
      {transactionComponents}
      <Button
        mode="outlined"
        onPress={() => {
          //need to change to list of transactions screen
          navigation.push('transactions');
        }}
        color="#007AE1">
        Show More
      </Button>
    </View>
  );
};

const styles = StyleSheet.create({
  top: {
    marginTop: 10,
  },
  text: {
    fontSize: 16,
    fontWeight: '300',
  },
});