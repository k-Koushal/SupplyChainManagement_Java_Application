Let’s break down the Scala method for calculating the KPI “5G SA Bearer SETUP Fail# RedCap” and explain how the index value comparison is handled, especially since the formula in the image mentions comparing values at the same index across arrays. I’ll walk through the code step-by-step and clarify the index comparison logic.
Code Recap
Here’s the method you provided, working with strings:
def calculate5GSABearerSetupFailRedCap: Int = {
  // Helper function to convert a semicolon-delimited string to an array of strings
  def toStringArray(arrayStr: String): Array[String] = {
    if (arrayStr.isEmpty) Array.empty[String]
    else arrayStr.split(";")
  }

  // Convert KPI strings to arrays of strings
  val initialSetupResultArray = toStringArray(CUCP_INITIAL_CTXT_ESTAB_DRSL_DRB_SETUP_RESULT_ARRAY)
  val modifySetupResultArray = toStringArray(CUCP_PDU_SESSION_RESOURCE_MODIFY_DRB_SETUP_RESULT_ARRAY)
  val setupDrslResultArray = toStringArray(CUCP_PDU_SESSION_RESOURCE_SETUP_DRSL_DRB_SETUP_RESULT_ARRAY)

  // Calculate KPI for the "Initial Context" part
  val initialKpi = if (CUCP_INITIAL_CTXT_ESTAB_UE_MOBILITY_GROUP_ID == "8") {
    initialSetupResultArray.count(_ == "2")
  } else {
    0
  }

  // Calculate KPI for the "Modify" part
  val modifyKpi = if (CUCP_PDU_SESSION_RESOURCE_MODIFY_UE_MOBILITY_GROUP_ID == "8") {
    modifySetupResultArray.count(_ == "2")
  } else {
    0
  }

  // Calculate KPI for the "Setup" part
  val setupKpi = if (CUCP_PDU_SESSION_RESOURCE_SETUP_UE_MOBILITY_GROUP_ID == "8") {
    setupDrslResultArray.count(_ == "2")
  } else {
    0
  }

  // Sum up all contributions to get the final KPI
  initialKpi + modifyKpi + setupKpi
}
Explanation of the Code
1. Helper Function: `toStringArray`
def toStringArray(arrayStr: String): Array[String] = {
  if (arrayStr.isEmpty) Array.empty[String]
  else arrayStr.split(";")
}
	•	Purpose: This function takes a semicolon-delimited string (e.g., "1;2;3") and converts it into an array of strings (e.g., Array("1", "2", "3")).
	•	Behavior:
	◦	If the input string is empty, it returns an empty array to avoid errors.
	◦	Otherwise, it splits the string on the ; delimiter using the split method.
	•	Usage: This is applied to each KPI array (e.g., CUCP_INITIAL_CTXT_ESTAB_DRSL_DRB_SETUP_RESULT_ARRAY) to convert them into arrays of strings for processing.
2. Converting KPI Strings to Arrays
val initialSetupResultArray = toStringArray(CUCP_INITIAL_CTXT_ESTAB_DRSL_DRB_SETUP_RESULT_ARRAY)
val modifySetupResultArray = toStringArray(CUCP_PDU_SESSION_RESOURCE_MODIFY_DRB_SETUP_RESULT_ARRAY)
val setupDrslResultArray = toStringArray(CUCP_PDU_SESSION_RESOURCE_SETUP_DRSL_DRB_SETUP_RESULT_ARRAY)
	•	What’s Happening: Each KPI array (assumed to be a string like "1;2;3") is converted into an array of strings using the toStringArray function.
	•	Example: If CUCP_INITIAL_CTXT_ESTAB_DRSL_DRB_SETUP_RESULT_ARRAY is "1;2;3", then initialSetupResultArray becomes Array("1", "2", "3").
3. Calculating the KPI for Each Part
The formula has three parts: Initial Context, Modify, and Setup. Let’s look at one part (they’re similar):
val initialKpi = if (CUCP_INITIAL_CTXT_ESTAB_UE_MOBILITY_GROUP_ID == "8") {
  initialSetupResultArray.count(_ == "2")
} else {
  0
}
	•	Condition Check: First, it checks if CUCP_INITIAL_CTXT_ESTAB_UE_MOBILITY_GROUP_ID equals "8". This is a string comparison since you requested to work with strings only.
	•	Counting: If the condition is true, it uses the count method on initialSetupResultArray to count how many elements in the array are equal to "2".
	◦	The _ == "2" is a shorthand for a function that takes an element and checks if it equals "2".
	◦	For example, if initialSetupResultArray is Array("1", "2", "3"), count(_ == "2") returns 1 because only one element ("2") matches.
	•	Else: If the mobility group ID is not "8", the contribution to the KPI is 0.
The same logic is applied to the “Modify” and “Setup” parts using their respective arrays and mobility group IDs.
4. Summing the Results
initialKpi + modifyKpi + setupKpi
	•	The final KPI is the sum of the counts from all three parts (Initial Context, Modify, and Setup).

Addressing the Index Value Comparison
The formula in the image states:
	•	“For each index of CUCP_PDU_SESSION_RESOURCE_MODIFY_DRB_SETUP_MAPPED_SQI_ARRAY, if the same index of CUCP_PDU_SESSION_RESOURCE_MODIFY_DRB_SETUP_RESULT_ARRAY = 2 AND CUCP_PDU_SESSION_RESOURCE_MODIFY_UE_MOBILITY_GROUP_ID = 8, then 1 else 0.”
This suggests that we need to compare values at the same index between two arrays (_MAPPED_SQI_ARRAY and _SETUP_RESULT_ARRAY). However, the current code does not perform this index-based comparison because:
	1	The _MAPPED_SQI_ARRAY is not used in the condition: The formula mentions CUCP_PDU_SESSION_RESOURCE_MODIFY_DRB_SETUP_MAPPED_SQI_ARRAY, but the condition only checks the _SETUP_RESULT_ARRAY for the value 2 and the UE_MOBILITY_GROUP_ID for 8. The _MAPPED_SQI_ARRAY doesn’t seem to play a role in the decision—it’s just mentioned as the array whose indices we iterate over.
	2	Simplified Logic: The current implementation interprets the KPI as counting how many times the _SETUP_RESULT_ARRAY has a value of 2, without needing to compare with the _MAPPED_SQI_ARRAY at the same index.
How the Code Handles Indices (or Doesn’t)
	•	Current Behavior: The code uses count(_ == "2"), which checks each element in the _SETUP_RESULT_ARRAY independently, without reference to the _MAPPED_SQI_ARRAY or its indices.
	•	Missing Index Comparison: The formula implies that for each index i, we should look at _MAPPED_SQI_ARRAY(i) and _SETUP_RESULT_ARRAY(i), but since _MAPPED_SQI_ARRAY isn’t used in the condition, we’re not doing a direct index-to-index comparison.
Correcting the Index Value Comparison
To align with the formula’s intent, we should iterate over the indices of the _MAPPED_SQI_ARRAY and check the corresponding value in the _SETUP_RESULT_ARRAY. Let’s revise the method to include this:
def calculate5GSABearerSetupFailRedCap: Int = {
  // Helper function to convert a semicolon-delimited string to an array of strings
  def toStringArray(arrayStr: String): Array[String] = {
    if (arrayStr.isEmpty) Array.empty[String]
    else arrayStr.split(";")
  }

  // Convert KPI strings to arrays of strings
  val initialSetupResultArray = toStringArray(CUCP_INITIAL_CTXT_ESTAB_DRSL_DRB_SETUP_RESULT_ARRAY)
  val modifySetupResultArray = toStringArray(CUCP_PDU_SESSION_RESOURCE_MODIFY_DRB_SETUP_RESULT_ARRAY)
  val setupDrslResultArray = toStringArray(CUCP_PDU_SESSION_RESOURCE_SETUP_DRSL_DRB_SETUP_RESULT_ARRAY)
  // Include the SQI arrays for index iteration
  val initialSqiArray = toStringArray(CUCP_INITIAL_CTXT_ESTAB_DRSL_DRB_MAPPED_SQI_ARRAY)
  val modifySqiArray = toStringArray(CUCP_PDU_SESSION_RESOURCE_MODIFY_DRB_SETUP_MAPPED_SQI_ARRAY)
  val setupSqiArray = toStringArray(CUCP_PDU_SESSION_RESOURCE_SETUP_DRSL_DRB_MAPPED_SQI_ARRAY)

  // Calculate KPI for the "Initial Context" part
  val initialKpi = if (CUCP_INITIAL_CTXT_ESTAB_UE_MOBILITY_GROUP_ID == "8") {
    // Iterate over indices of SQI array and check corresponding SETUP_RESULT value
    initialSqiArray.indices.count { idx =>
      initialSetupResultArray.lift(idx).contains("2")
    }
  } else {
    0
  }

  // Calculate KPI for the "Modify" part
  val modifyKpi = if (CUCP_PDU_SESSION_RESOURCE_MODIFY_UE_MOBILITY_GROUP_ID == "8") {
    // Iterate over indices of SQI array and check corresponding SETUP_RESULT value
    modifySqiArray.indices.count { idx =>
      modifySetupResultArray.lift(idx).contains("2")
    }
  } else {
    0
  }

  // Calculate KPI for the "Setup" part
  val setupKpi = if (CUCP_PDU_SESSION_RESOURCE_SETUP_UE_MOBILITY_GROUP_ID == "8") {
    // Iterate over indices of SQI array and check corresponding SETUP_RESULT value
    setupSqiArray.indices.count { idx =>
      setupDrslResultArray.lift(idx).contains("2")
    }
  } else {
    0
  }

  // Sum up all contributions to get the final KPI
  initialKpi + modifyKpi + setupKpi
}

Explanation of the Revised Code
1. Including the SQI Arrays
val initialSqiArray = toStringArray(CUCP_INITIAL_CTXT_ESTAB_DRSL_DRB_MAPPED_SQI_ARRAY)
val modifySqiArray = toStringArray(CUCP_PDU_SESSION_RESOURCE_MODIFY_DRB_SETUP_MAPPED_SQI_ARRAY)
val setupSqiArray = toStringArray(CUCP_PDU_SESSION_RESOURCE_SETUP_DRSL_DRB_MAPPED_SQI_ARRAY)
	•	These arrays are now included because the formula specifies that we iterate over their indices.
2. Index-Based Comparison
For the “Initial Context” part (similar logic applies to Modify and Setup):
val initialKpi = if (CUCP_INITIAL_CTXT_ESTAB_UE_MOBILITY_GROUP_ID == "8") {
  initialSqiArray.indices.count { idx =>
    initialSetupResultArray.lift(idx).contains("2")
  }
} else {
  0
}
	•	initialSqiArray.indices: This gives us the range of indices for initialSqiArray (e.g., if the array has 3 elements, indices is 0 to 2).
	•	Index Lookup:
	◦	initialSetupResultArray.lift(idx): The lift method safely accesses the element at index idx in initialSetupResultArray. It returns an Option[String]—Some(value) if the index exists, or None if it doesn’t (e.g., if the array is shorter).
	◦	.contains("2"): This checks if the value at that index is "2". If the index doesn’t exist (i.e., lift returns None), contains safely returns false.
	•	Counting: The count method counts how many indices satisfy the condition (i.e., where the corresponding value in initialSetupResultArray is "2").
3. Why This Matches the Formula
	•	The formula says: “For each index of CUCP_INITIAL_CTXT_ESTAB_DRSL_DRB_MAPPED_SQI_ARRAY, if the same index of CUCP_INITIAL_CTXT_ESTAB_DRSL_DRB_SETUP_RESULT_ARRAY = 2 AND CUCP_INITIAL_CTXT_ESTAB_UE_MOBILITY_GROUP_ID = 8, then 1 else 0.”
	•	The revised code:
	◦	Iterates over the indices of the _MAPPED_SQI_ARRAY.
	◦	For each index, checks the value at the same index in the _SETUP_RESULT_ARRAY.
	◦	Counts a 1 if the value is "2" and the UE_MOBILITY_GROUP_ID is "8", otherwise 0.
4. Edge Case Handling
	•	If _SETUP_RESULT_ARRAY is shorter than _MAPPED_SQI_ARRAY, lift(idx) will return None for indices beyond its length, and contains("2") will return false, effectively treating missing values as not contributing to the KPI.
	•	If _SETUP_RESULT_ARRAY is longer, the extra elements are ignored because we only iterate up to the length of _MAPPED_SQI_ARRAY.

Example Walkthrough
Let’s say:
	•	CUCP_INITIAL_CTXT_ESTAB_DRSL_DRB_MAPPED_SQI_ARRAY = "10;20;30" → initialSqiArray = Array("10", "20", "30")
	•	CUCP_INITIAL_CTXT_ESTAB_DRSL_DRB_SETUP_RESULT_ARRAY = "1;2;1" → initialSetupResultArray = Array("1", "2", "1")
	•	CUCP_INITIAL_CTXT_ESTAB_UE_MOBILITY_GROUP_ID = "8"
Initial KPI Calculation:
	•	initialSqiArray.indices is 0, 1, 2.
	•	Index 0: initialSetupResultArray.lift(0) → Some("1"), contains("2") → false.
	•	Index 1: initialSetupResultArray.lift(1) → Some("2"), contains("2") → true.
	•	Index 2: initialSetupResultArray.lift(2) → Some("1"), contains("2") → false.
	•	Total count: 1 (only index 1 satisfies the condition).
If the other parts (Modify and Setup) have similar data, their counts are computed the same way, and the final KPI is the sum.

Why the Original Code Didn’t Do Index Comparison
The original code simply counted occurrences of "2" in each _SETUP_RESULT_ARRAY without reference to the _MAPPED_SQI_ARRAY. This effectively ignored the “for each index of _MAPPED_SQI_ARRAY” part of the formula, treating the KPI as a simple count of failures ("2") rather than an index-based comparison.
The revised code corrects this by explicitly iterating over the indices of the _MAPPED_SQI_ARRAY and checking the corresponding value in the _SETUP_RESULT_ARRAY, which aligns with the formula’s intent.
Let me know if you need further clarification!
