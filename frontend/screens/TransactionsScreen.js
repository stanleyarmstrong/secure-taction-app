import React from 'react';
import {View, FlatList, StyleSheet, useColorScheme} from 'react-native';
import {Card, DataTable} from 'react-native-paper';

const TransactionsScreen = () => {
  // replace with a state call here, and the map will happen in the api call
  // also polish the data table rows
  const transactions = [
    {
      transactionId: 0,
      accountId: 0,
      amount: -100.59,
      address: 'In Person',
      vendor: "Ralph's Grocery",
      categories: ['Grocery'],
    },
    {
      transactionId: 1,
      accountId: 1,
      amount: 1500.67,
      address: 'Direct Deposit',
      vendor: 'Cisco Systems',
      categories: ['Paycheck'],
    },
    {
      transactionId: 2,
      accountId: 2,
      amount: -14.29,
      address: 'In Person',
      vendor: 'Grocery Outlet',
      categories: ['Grocery'],
    },
  ].map((transaction) => {
    return (
      <DataTable.Row key={transaction.transactionId}>
        <DataTable.Cell> {transaction.vendor} </DataTable.Cell>
        <DataTable.Cell> {transaction.address}</DataTable.Cell>
        <DataTable.Cell numeric> {transaction.amount}</DataTable.Cell>
      </DataTable.Row>
    );
  });
  return (
    <View style={styles.shell}>
      <Card style={styles.inner}>
        <Card.Title
          title="Recent Activity"
          style={styles.title}
          titleStyle={styles.titleColor}
        />
        <Card.Content>
          <DataTable>
            <DataTable.Header>
              <DataTable.Title> Vendor </DataTable.Title>
              <DataTable.Title> Type </DataTable.Title>
              <DataTable.Title numeric> Amount </DataTable.Title>
            </DataTable.Header>
            {transactions}
          </DataTable>
        </Card.Content>
      </Card>
    </View>
  );
};

const styles = StyleSheet.create({
  shell: {
    backgroundColor: '#E9E9E9',
    height: '100%',
  },
  inner: {
    shadowColor: '#000000',
    shadowOpacity: 20,
    borderRadius: 12,
    marginTop: 50,
    marginLeft: 25,
    marginRight: 25,
  },
  title: {
    color: '#FFFFFF',
    backgroundColor: '#00A7E1',
    fontSize: 24,
    borderRadius: 12,
  },
  titleColor: {
    color: '#FFFFFF',
  },
  newCard: {
    paddingTop: 10,
    textAlign: 'center',
  },
});

export default TransactionsScreen;
