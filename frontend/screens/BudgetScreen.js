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
  const {accountId, budgetId} = route.params;
  //replace with state call
  useEffect(() => {
    getAccount(accountId)
      .then((data) => {
        setAccount(data);
      })
      .catch((error) => {
        console.error(error);
      });
    getBudget(budgetId)
      .then((data) => {
        setBudget(data);
      })
      .catch((error) => {
        setBudget({});
      });
  }, [accountId, budgetId]);
  const middle =
    Object.keys(budget).length === 0 || Object.keys(budget).length === 5 ? (
      <AddBudget id={accountId} />
    ) : (
      <Budget
        alert={budget.minimumAlert}
        cancel={budget.autoCancel}
        currentBudget={budget.currentBudgetBalance}
        maxBudget={budget.maxBudgetBalance}
      />
    );
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
            budget={budget.currentBudgetBalance}
          />
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
