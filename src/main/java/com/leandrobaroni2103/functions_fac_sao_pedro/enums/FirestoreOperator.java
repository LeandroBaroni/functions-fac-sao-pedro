package com.leandrobaroni2103.functions_fac_sao_pedro.enums;

public enum FirestoreOperator {
  EQUAL("=="),
  NOT_EQUAL("!="),
  GREATER_THAN(">"),
  GREATER_THAN_OR_EQUAL(">="),
  LESS_THAN("<"),
  LESS_THAN_OR_EQUAL("<="),
  ARRAY_CONTAINS("array-contains"),
  IN("in"),
  ARRAY_CONTAINS_ANY("array-contains-any"),
  NOT_IN("not-in");

  private final String symbol;

  FirestoreOperator(String symbol) {
    this.symbol = symbol;
  }

  public String getSymbol() {
    return symbol;
  }

  public static FirestoreOperator fromSymbol(String symbol) {
    for (FirestoreOperator op : values()) {
      if (op.symbol.equals(symbol)) {
        return op;
      }
    }
    throw new IllegalArgumentException("Unsupported operator: " + symbol);
  }
}
