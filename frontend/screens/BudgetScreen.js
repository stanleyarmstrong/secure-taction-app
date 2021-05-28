import React, {useState, useEffect} from 'react';
import {View, StyleSheet, useColorScheme} from 'react-native';
import {Card, Divider} from 'react-native-paper';
import BankInfo from '../components/bankinfo';
import {AddBudget, Budget} from '../components/budget';
import {RecentActivity} from '../components/recentactivity';
import {getAccount} from '../services/accountService';
import {getBudget} from '../services/budgetService';

const BudgetScreen = ({route}) => {
  const [account, setAccount] = useState({});
  const [budget, setBudget] = useState({});
  //replace with state call
  useEffect(() => {
    const {accountId, budgetId} = route.params;
    getAccount(accountId)
      .then((data) => {
        setAccount(data);
      })
      .catch((error) => {
        console.error(error);
      });
    getBudget(accountId, budgetId)
      .then((data) => {
        setBudget(data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, [route.params]);
  /*
  const middle = budget ? (
    <AddBudget />
  ) : (
      <Budget
        alert={alert}
        cancel={cancel}
        currentBudget={currentBudget}
        maxBudget={maxBudget}
      />
    );
  */
  console.log(account);
  console.log(budget);
  return (
    <View style={styles.shell}>
      <Card style={styles.inner}>
        <Card.Title
          title={account.accountName}
          style={styles.title}
          titleStyle={styles.titleColor}
        />
        <Card.Content>
          <BankInfo
            balance={account.balance}
            budget={0}
          />
          <Divider />
          <AddBudget />
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
