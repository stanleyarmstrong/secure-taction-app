import React from 'react';
import {View, StyleSheet, useColorScheme} from 'react-native';
import {Card, Divider} from 'react-native-paper';
import BankInfo from '../components/bankinfo';
import {AddBudget, Budget} from '../components/budget';
import {RecentActivity} from '../components/recentactivity';

const BudgetScreen = ({route}) => {
  //replace with state call
  const {
    accountName,
    bank,
    alert,
    cancel,
    maxBudget,
    currentBudget,
    set,
  } = route.params;
  const middle =
    set === false ? (
      <AddBudget />
    ) : (
      <Budget
        alert={alert}
        cancel={cancel}
        currentBudget={currentBudget}
        maxBudget={maxBudget}
      />
    );
  return (
    <View style={styles.shell}>
      <Card style={styles.inner}>
        <Card.Title
          title={accountName}
          style={styles.title}
          titleStyle={styles.titleColor}
        />
        <Card.Content>
          <BankInfo balance={3000} bank={bank} budget={currentBudget} />
          <Divider />
          {middle}
          <Divider />
          <RecentActivity />
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
