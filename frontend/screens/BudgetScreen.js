import React from 'react';
import {View, StyleSheet, useColorScheme} from 'react-native';
import {Card, Button, Divider, Text} from 'react-native-paper';
import {useNavigation} from '@react-navigation/native';
import BankInfo from '../components/bankinfo';
import {AddBudget, Budget} from '../components/budget';

const BudgetScreen = ({route}) => {
  const {accountName, bank, progress} = route.params;
  const middle = progress === 1 ? <AddBudget /> : <Budget />;
  return (
    <View style={styles.shell}>
      <Card style={styles.inner}>
        <Card.Title
          title={accountName}
          style={styles.title}
          titleStyle={styles.titleColor}
        />
        <Card.Content>
          <BankInfo balance={3000} bank={bank} />
          <Divider />
          {middle}
          <Divider />
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

export default BudgetScreen;
