import React, {useState, useEffect, useRef} from 'react';
import {View, StyleSheet, useColorScheme} from 'react-native';
import {useNavigation} from '@react-navigation/native';
import {Card, Text, DataTable, Divider, Button} from 'react-native-paper';

const FraudAlertScreen = ({route}) => {
  // replace with state call
  const {vendor, amount, account} = route.params;
  const [yes1, setYes1] = useState(false);
  const [no1, setNo1] = useState(false);
  const [yes2, setYes2] = useState(false);
  const [no2, setNo2] = useState(false);
  const navigation = useNavigation();
  const transaction = (
    <View>
      <DataTable.Row>
        <DataTable.Cell>Vendor: </DataTable.Cell>
        <DataTable.Cell> {vendor} </DataTable.Cell>
      </DataTable.Row>
      <DataTable.Row>
        <DataTable.Cell>Amount: </DataTable.Cell>
        <DataTable.Cell> ${amount.toFixed(2)} </DataTable.Cell>
      </DataTable.Row>
      <DataTable.Row>
        <DataTable.Cell>Account: </DataTable.Cell>
        <DataTable.Cell> {account} </DataTable.Cell>
      </DataTable.Row>
      <Text style={styles.buttonText}> Was this you? </Text>
      <View style={styles.buttons}>
        <Button
          mode="contained"
          labelStyle={styles.label}
          color="#4FE7AC"
          onPress={() => setYes1(true)}>
          Yes
        </Button>
        <Button
          mode="contained"
          labelStyle={styles.label}
          color="#FF7A72"
          onPress={() => setNo1(true)}>
          No
        </Button>
      </View>
    </View>
  );
  const thanks = (
    <View>
      <Text style={styles.upper}> Thanks have a nice day! </Text>
      <Divider />
      <Button
        style={styles.buttons}
        mode="contained"
        onPress={() => {
          navigation.popToTop();
        }}>
        Return Home
      </Button>
    </View>
  );
  const label = (
    <View>
      <Text style={styles.upper}> The transaction has been cancelled. </Text>
      <Divider />
      <Text style={styles.buttonText}>
        Would you like to label this as fraud?
      </Text>
      <View style={styles.buttons}>
        <Button
          mode="contained"
          labelStyle={styles.label}
          color="#4FE7AC"
          onPress={() => setYes2(true)}>
          Yes
        </Button>
        <Button
          mode="contained"
          labelStyle={styles.label}
          color="#FF7A72"
          onPress={() => setNo2(true)}>
          No
        </Button>
      </View>
    </View>
  );
  const fraud = (
    <View>
      <Text style={styles.upper}>
        Your bank was notified of the fraudulent transactions and will be in
        contact with you shortly!
      </Text>
      <Divider />
      <Button
        style={styles.buttons}
        mode="contained"
        onPress={() => {
          navigation.popToTop();
        }}>
        Return Home
      </Button>
    </View>
  );
  return (
    <View style={styles.shell}>
      <Card style={styles.inner}>
        <Card.Title
          title="Transaction Details"
          style={styles.title}
          titleStyle={styles.titleColor}
        />
        <Card.Content>
          {yes1
            ? thanks
            : no1
            ? no2
              ? thanks
              : yes2
              ? fraud
              : label
            : transaction}
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
  buttons: {
    marginTop: 10,
    flexDirection: 'row',
    justifyContent: 'space-evenly',
  },
  buttonText: {
    alignSelf: 'center',
    marginTop: 15,
    fontSize: 16,
    fontWeight: '700',
  },
  upper: {
    alignSelf: 'center',
    margin: 10,
    fontSize: 16,
  },
  return: {
    fontWeight: '700',
    backgroundColor: '#00A7E1',
    color: '#FFFFFF',
  },
  label: {
    color: '#FFFFFF',
  },
});

export default FraudAlertScreen;
