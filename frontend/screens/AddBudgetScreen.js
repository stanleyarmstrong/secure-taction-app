import React, {useState} from 'react';
import {View, StyleSheet, useColorScheme} from 'react-native';
import {Card, Button, TextInput} from 'react-native-paper';
import {useNavigation} from '@react-navigation/native';
import {addBudget} from '../services/budgetService';

const AddBudgetScreen = ({route}) => {
  const navigation = useNavigation();
  // do with state shit: need to add a useEffect to get the just the user and account ids
  const {accountId} = route.params;
  const [maxBudgetBalance, setMaxBudgetBalance] = useState('');
  const currentBudgetBalance = 0;
  const [minimumAlert, setMinimumAlert] = useState('');
  const [autoCancel, setAutoCancel] = useState('');
  const [budgetName, setBudgetName] = useState('');
  return (
    <View style={styles.shell}>
      <Card style={styles.inner}>
        <Card.Title
          title="Set Your Values"
          style={styles.title}
          titleStyle={styles.titleColor}
        />
        <Card.Content style={styles.content}>
          <TextInput
            mode="outlined"
            value={budgetName}
            label="Budget's Name"
            placeholder="ex: Budget's Name"
            onChangeText={(name) => setBudgetName(name)}
            theme={theme}
            style={styles.input}
          />
          <TextInput
            mode="outlined"
            value={maxBudgetBalance}
            label="Maximum Spending"
            placeholder="ex: 1050.00"
            left={<TextInput.Affix text="$" />}
            onChangeText={(mbb) => setMaxBudgetBalance(mbb)}
            theme={theme}
            style={styles.input}
          />
          <TextInput
            mode="outlined"
            value={minimumAlert}
            label="Minimum Alert"
            placeholder="ex: 200.00"
            left={<TextInput.Affix text="$" />}
            onChangeText={(ma) => setMinimumAlert(ma)}
            theme={theme}
            style={styles.input}
          />
          <TextInput
            mode="outlined"
            value={autoCancel}
            label="Automatic Cancel"
            placeholder="ex: 1050.00"
            left={<TextInput.Affix text="$" />}
            onChangeText={(ac) => setAutoCancel(ac)}
            theme={theme}
            style={styles.input}
          />
          <Button
            mode="contained"
            color="#389635"
            dark
            onPress={() => {
              //need to add user id and account id to object
              const obj = {
                userId: '5CC6D297-2415-4A36-8E61-79C011C3C9EF',
                accountId: accountId,
                maxBudgetBalance: maxBudgetBalance,
                currentBudgetBalance: currentBudgetBalance,
                minimumAlert: minimumAlert,
                autoCancel: autoCancel,
                budgetName: budgetName,
              };
              // service call instead of console.log
              addBudget(obj);
              navigation.pop();
            }}>
            Done
          </Button>
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
    justifyContent: 'space-evenly',
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
  input: {
    marginTop: 5,
    marginBottom: 10,
  },
});

const theme = {
  colors: {
    primary: '#00A7E1',
    background: '#FFFFFF',
  },
};

export default AddBudgetScreen;
