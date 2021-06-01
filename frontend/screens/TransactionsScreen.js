import React, {useState, useEffect} from 'react';
import {View, FlatList, StyleSheet, useColorScheme} from 'react-native';
import {Card, DataTable} from 'react-native-paper';
import {getTransactions} from '../services/transactionservice';

const TransactionsScreen = ({route}) => {
  const [transactions, setTransactions] = useState([]);
  const [page, setPage] = useState(0);
  const itemsPerPage = 8;
  const from = page * itemsPerPage;
  const to = (page + 1) * itemsPerPage;
  useEffect(() => {
    getTransactions(route.params.accountId).then((data) => {
      setTransactions(
        data.map((transaction) => {
          return (
            <View>
              <DataTable.Row key={transaction.transactionId}>
                <DataTable.Cell numeric={false}>
                  {transaction.vendor}
                </DataTable.Cell>
                <DataTable.Cell> {transaction.type} </DataTable.Cell>
                <DataTable.Cell numeric={true}>
                  ${transaction.amount > 0 ? '-' : ''}
                  {transaction.amount.toFixed(2)}
                </DataTable.Cell>
              </DataTable.Row>
            </View>
          );
        }),
      );
    });
  }, [route.params]);
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
            {transactions.slice(from, to)}
            <DataTable.Pagination
              page={page}
              numberofPages={Math.floor(transactions.length / itemsPerPage)}
              onPageChange={(p) => {
                setPage(p);
              }}
              label={
                from + '-' + to + ' of ' + transactions.length + ' transactions'
              }
            />
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
